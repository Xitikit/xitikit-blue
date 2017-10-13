package org.xitikit.blue.graphapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.xitikit.blue.graphapi.gifnoc.GraphApiClientProperties;
import org.xitikit.blue.graphapi.model.*;
import org.xitikit.blue.graphapi.service.IAzureCustomAttributeCacheService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.*;

/**
 * Copyright Xitikit.org 2017
 *
 * Client which provides CRUD functionality for working with the Azure AD B2C Graph API.
 *
 * @author J. Keith Hoopes
 */
@SuppressWarnings({"unused", "WeakerAccess", "EmptyTryBlock"})
@Slf4j
@Service("azureGraphApiClient")
public class AzureGraphApiClient{

  private static final String USERS_PATH = "/users/";

  private RestTemplate restTemplate;

  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private IAzureCustomAttributeCacheService azureCustomAttributeCacheService;

  @Autowired
  private GraphApiClientProperties graphApiClientProperties;

  @PostConstruct
  public void init(){

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    int timeout = graphApiClientProperties.getTimeout();
    requestFactory.setConnectTimeout(timeout);
    requestFactory.setReadTimeout(timeout);

    restTemplate = new RestTemplate(requestFactory);
  }

  public void deleteUser(String userId){

    AccessToken accessToken = getAccessToken();
    String fullUrl = getFullUrl(USERS_PATH + userId);
    HttpHeaders headers = createHeaders(accessToken);
    HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
    if(log.isDebugEnabled()){
      log.debug("Attempting to delete user: " + userId);
      log.debug("accessToken: " + accessToken);
      log.debug("fullUrl: " + fullUrl);
      log.debug("headers: " + headers);
      log.debug("requestEntity: " + requestEntity);
    }
    restTemplate.exchange(fullUrl, HttpMethod.DELETE, requestEntity, String.class);
  }

  public AccessToken getAccessToken(){

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    String clientId = graphApiClientProperties.getClientId();
    String clientSecret = graphApiClientProperties.getClientSecret();
    String baseUrl = graphApiClientProperties.getBaseUrl();

    params.add("client_id", clientId);
    params.add("client_secret", clientSecret);
    params.add("resource", baseUrl);
    params.add("grant_type", "client_credentials");
    params.add("scope", "Directory.AccessAsUser.All");

    return restTemplate.postForObject(accessTokenUrl(), params, AccessToken.class);
  }

  private String accessTokenUrl(){

    return "https://login.microsoftonline.com/" + graphApiClientProperties.getTenantId() + "/oauth2/token";
  }

  private String getFullUrl(String urlPart){

    String apiVersion = graphApiClientProperties.getApiVersion();
    if(urlPart.startsWith("/")){

      return baseUrl() + urlPart + "?api-version=" + apiVersion;
    }else{
      return baseUrl() + "/" + urlPart + "?api-version=" + apiVersion;
    }
  }

  private String baseUrl(){

    return combineUrlParts(graphApiClientProperties.getBaseUrl(), graphApiClientProperties.getTenantId());
  }

  /**
   * Makes sure there is at least one url part separator between the two combined parts.
   *
   * @param first Added to start of result.
   * @param second Added to end of result.
   *
   * @return The combined url.
   */
  private String combineUrlParts(String first, String second){

    assert first != null : "'first' parameter to combineUrlParts cannot be null.";
    assert second != null : "'second' parameter to combineUrlParts cannot be null.";

    if(!first.endsWith("/")){

      first = first + "/";
    }
    if(second.startsWith("/")){

      second = second.substring(1, second.length());
    }

    return first + second;
  }

  @SuppressWarnings("Duplicates")
  private HttpHeaders createHeaders(final AccessToken accessToken){

    final HttpHeaders headers = new HttpHeaders();
    if(accessToken != null){
      headers.add("Authorization", "Bearer " + accessToken.getAccessToken());
    }
    headers.add("Accept", "application/json");

    return headers;
  }

  public void updateUser(GraphApiUser userRequest){

    AccessToken accessToken = getAccessToken();
    JsonNode j = mapper.convertValue(userRequest, JsonNode.class);

    //        if (userRequest.getLinked() != null || userRequest.isCustomAttributeOneAvailable()) {
    //            Iterator<Map.Entry<String, JsonNode>> fields = j.fields();
    //            while (fields.hasNext()) {
    //                Map.Entry<String, JsonNode> entry = fields.next();
    //                ((ObjectNode) j).put(entry.getKey(), entry.getValue().textValue());
    //            }
    //        }
    //
    //        if (userRequest.getLinked() != null) {
    //            ((ObjectNode) j).remove("linked");
    //            try {
    //                ((ObjectNode) j).put(azureCustomAttributeCacheService.getLinkedAttributeName(), userRequest.getLinked());
    //            } catch (Exception e) {
    //                log.error("Error getting the name for custom attribute isLinked", e);
    //            }
    //
    //        }
    //        if (userRequest.isCustomAttributeOneAvailable()) {
    //            ((ObjectNode) j).remove("customAttributeOne");
    //            try {
    //                ((ObjectNode) j).put(azureCustomAttributeCacheService.getSignUpAttributeName(), userRequest.getCustomAttributeOne());
    //            } catch (Exception e) {
    //                log.error("Error getting the name for custom attribute is customAttributeOne", e);
    //            }
    //        }
    restTemplate.exchange(getFullUrl(USERS_PATH + userRequest.getId()), HttpMethod.PATCH, new HttpEntity<>(j, createHeaders(accessToken)), String.class);
  }

  public GraphApiUser getUser(String userId){

    AccessToken accessToken = getAccessToken();
    JsonNode response = restTemplate
                          .exchange(getFullUrl(USERS_PATH + userId), HttpMethod.GET, new HttpEntity<>(createHeaders(accessToken)), JsonNode.class)
                          .getBody();

    try{
      return populateCustomProperty(response);
    }catch(JsonProcessingException e){
      log.error("Error parsing response for get user", e);
      return null;
    }
  }

  private GraphApiUser populateCustomProperty(JsonNode jsonUser) throws JsonProcessingException{

    GraphApiUser azureUser = mapper.treeToValue(jsonUser, GraphApiUser.class);
    try{
      //            JsonNode customAttributeTwo = jsonUser.get(azureCustomAttributeCacheService.getLinkedAttributeName());
      //            if (customAttributeTwo != null) {
      //                azureUser.setLinked(customAttributeTwo.asText());
      //            }
    }catch(Exception e){
      log.error("Error getting the name for custom attribute isLinked", e);
    }

    try{
      //            JsonNode customAttributeOneProperty = jsonUser.get(azureCustomAttributeCacheService.getSignUpAttributeName());
      //            if (customAttributeOneProperty != null) {
      //                azureUser.setCustomAttributeOne(customAttributeOneProperty.asText());
      //            }
    }catch(Exception e){

      log.error("Error getting the name for custom attribute customAttributeOne", e);
    }
    return azureUser;
  }

  public PaginatedUsers getUsers(String filterExpression){

    return getUsers(filterExpression, null, null);
  }

  public PaginatedUsers getUsers(String filterExpression, Integer pageSize, String nextToken){

    AccessToken accessToken = getAccessToken();
    List<GraphApiUser> users = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    if(pageSize != null){
      sb
        .append("&$top=")
        .append(pageSize);
    }

    if(hasText(nextToken)){
      sb
        .append("&$skiptoken=")
        .append(nextToken);
    }

    sb.append(hasText(filterExpression) ? "&" + filterExpression : "");

    JsonNode response = restTemplate
                          .exchange(getFullUrl("/users") + sb.toString(), HttpMethod.GET, new HttpEntity<>(createHeaders(accessToken)), JsonNode.class)
                          .getBody();
    PaginatedUsers paginatedUsers;
    try{
      paginatedUsers = mapper.treeToValue(response, PaginatedUsers.class);
    }catch(JsonProcessingException e){
      log.error("Error parsing response for get user with filter: " + filterExpression, e);
      return new PaginatedUsers();
    }

    if(response.get("value") != null){
      ArrayNode arrayNode = (ArrayNode) response.get("value");
      for(int i = 0; i < arrayNode.size(); i++){
        JsonNode jsonNode = arrayNode.get(i);
        try{
          users.add(populateCustomProperty(jsonNode));
        }catch(JsonProcessingException e){
          log.error("Error parsing response for get user with filter: " + filterExpression, e);
        }
      }
    }
    paginatedUsers.setUsers(users);
    return paginatedUsers;
  }

  public Applications getApplications(){

    AccessToken accessToken = getAccessToken();
    return restTemplate
             .exchange(getFullUrl("/applications"), HttpMethod.GET, new HttpEntity<>(createHeaders(accessToken)), Applications.class)
             .getBody();
  }

  public ExtensionProperties getExtensionProperties(String extensionAppId){

    AccessToken accessToken = getAccessToken();
    return restTemplate
             .exchange(getFullUrl("/applications/" + extensionAppId + "/extensionProperties"), HttpMethod.GET, new HttpEntity<>(createHeaders(accessToken)), ExtensionProperties.class)
             .getBody();
  }
}

