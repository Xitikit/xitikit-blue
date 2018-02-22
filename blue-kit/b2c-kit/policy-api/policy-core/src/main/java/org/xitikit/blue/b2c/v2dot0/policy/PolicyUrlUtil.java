package org.xitikit.blue.b2c.v2dot0.policy;

import javax.annotation.Nonnull;
import java.util.Arrays;

import static java.util.stream.Collectors.*;

public final class PolicyUrlUtil{

    public final static class Defaults{

        public static final String POLICY_BASE = "/blue-kit/policy";

        public static final String EDIT_PROFILE_BASE = POLICY_BASE + "/edit-profile";

        public static final String RESET_PASSWORD_BASE = POLICY_BASE + "/reset-password";

        public static final String SIGN_IN_BASE = POLICY_BASE + "/sign-in";

        public static final String SIGN_OUT_BASE = POLICY_BASE + "/sign-out";

        public static final String SIGN_UP_BASE = POLICY_BASE + "/sign-up";

        public static final String SIGN_UP_OR_SIGN_IN_BASE = POLICY_BASE + "/sign-up-or-sign-in";
    }

    public static String combineRelativePaths(final String... relativePaths){

        return relativePaths == null || relativePaths.length <= 0 ? "" : Arrays
            .stream(relativePaths)
            .map(PolicyUrlUtil::checkRelativePath)
            .collect(joining());
    }

    @Nonnull
    public static String checkPolicyBasePath(final String policyBasePath){

        return checkRelativePath(
            checkRelativePath(policyBasePath),
            Defaults.POLICY_BASE);
    }

    @Nonnull
    public static String checkEditProfilePath(final String editProfilePath){

        return checkRelativePath(
            checkRelativePath(editProfilePath),
            Defaults.EDIT_PROFILE_BASE);
    }

    @Nonnull
    public static String checkResetPasswordPath(final String resetPasswordPath){

        return checkRelativePath(
            checkRelativePath(resetPasswordPath),
            Defaults.RESET_PASSWORD_BASE);
    }

    @Nonnull
    public static String checkSignInPath(final String signInPath){

        return checkRelativePath(
            checkRelativePath(signInPath),
            Defaults.SIGN_IN_BASE);
    }

    @Nonnull
    public static String checkSignOutPath(final String signOutPath){

        return checkRelativePath(
            checkRelativePath(signOutPath),
            Defaults.SIGN_OUT_BASE);
    }

    @Nonnull
    public static String checkSignUpPath(final String signUpPath){

        return checkRelativePath(
            checkRelativePath(signUpPath),
            Defaults.SIGN_UP_BASE);
    }

    @Nonnull
    public static String checkSignUpOrSignInPath(final String signUpOrSignInPath){

        return checkRelativePath(
            checkRelativePath(signUpOrSignInPath),
            Defaults.SIGN_UP_OR_SIGN_IN_BASE);
    }

    @Nonnull
    private static String checkRelativePath(
        @Nonnull final String relativePath,
        @Nonnull final String fallBackValue){

        return "".equals(relativePath)
            ? fallBackValue
            : relativePath;
    }

    private static String checkRelativePath(final String relativePath){

        final String answer = relativePath == null ? "" : relativePath.trim();
        if(!"".equals(answer)){
            if(!answer.startsWith("/")){
                throw new IllegalArgumentException("" +//because it looks nicer, that's why
                    "The 'relativePath' must start with a leading '/'. " +
                    "If the relative path is '/', then it should be set to blank instead.");
            }
            if(answer.endsWith("/")){
                throw new IllegalArgumentException("" +
                    "The 'relativePath' must NOT end with a '/'. " +
                    "If the relative path is '/', then it should be set to blank instead.");
            }
        }
        return answer;
    }
}
