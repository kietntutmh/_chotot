package com.vn.chotot.api.jira_services;

import org.testng.annotations.Test;

public class JiraApiUnitTest {
    @Test
    public void UT_commentIssueAttachImage() {
        String imagePath = System.getProperty("user.dir") + "/screenshots/" + "VUHOANG.jpeg";
        String imageName = "VUHOANG.jpeg";

        String issueKey = "GAM-1634";
        String tagUser = "vuhoang";
        String issueDetail = ",Vu hoang test attach image to comment";
        String modeName = "VUHOANG TEST";

        //        new JiraApiServices().commentIssueAttachImage(issueKey, tagUser, issueDetail,
        // modeName, imagePath);
    }

    @Test
    public void UT_createAndLinkNewIssue() {
        String projectKey = "GAM",
                userStoryKey = "GAM-1646",
                summary = "Unit Test: createAndLinkNewIssue - SUMMARY 2",
                description = "Unit Test: createAndLinkNewIssue - DESCRIPTION 2",
                assignee = "vuhoang",
                modeName = "UNIT TEST",
                imagePath = System.getProperty("user.dir") + "/screenshots/" + "VUHOANG.jpeg";
        new JiraApiServices()
                .createAndLinkNewIssue(
                        projectKey, userStoryKey, summary, description, assignee, modeName, imagePath);
    }

    @Test
    public void UT_createNewIssue() {
        String projectKey = "GAM",
                summary = "UNIT TEST: createNewIssue SUMMARY",
                description = "UNIT TEST: createNewIssue DESCRIPTION",
                assignee = "vuhoang";
        //        new JiraApiServices().createNewIssue(projectKey, summary, description, assignee);
    }

    @Test
    public void UT_createNewStory() {
        String projectKey = "GAM",
                summary = "UNIT TEST: createNewStory SUMMARY",
                description = "UNIT TEST: createNewStory DESCRIPTION",
                assignee = "vuhoang";
        //        new JiraApiServices().createNewStory(projectKey, summary, description, assignee);
    }

    @Test
    public void UT_getAllStoryKeysOfProject() {
        //        List<String> testStr = new JiraApiServices().getAllStoryKeysOfProject("GAM");
        //        testStr.stream().forEach(x -> System.out.println(x));
    }

    @Test
    public void UT_getAllStorySummaryOfProject() {
        //        List<String> testStr = new JiraApiServices().getAllStorySummaryOfProject("GAM");
        //        testStr.stream().forEach(x -> System.out.println(x));
    }

    @Test
    public void UT_getAllIssueKeysOfProject() {
        //        List<String> testStr = new JiraApiServices().getAllIssueKeysOfProject("GAM");
        //        testStr.stream().forEach(x -> System.out.println(x));
    }

    @Test
    public void UT_getAllIssueSummaryOfProject() {
        String storyKey = "GAM-1646";
        //        List<String> testList = new JiraApiServices().getAllIssueSummaryOfStory(storyKey);
        //        testList.stream().forEach(obj -> System.out.println(obj));
    }

    @Test
    public void UT_getAllIssueKeysOfStory() {
        String storyKey = "GAM-1646";
        //        List<String> testList = new JiraApiServices().getAllIssueKeysOfStory(storyKey);
        //        testList.stream().forEach(obj -> System.out.println(obj));
    }

    @Test
    public void UT_getStoryKey() {
        String storySummary = "Insert Ad - PTY JIRA_API_TEST";
        String storyKey = new JiraApiServices().getStoryKey("GAM", storySummary, "Desc", "vuhoang");
        System.out.println(storyKey);
    }

    @Test
    public void UT_getAllIssueDescriptionOfStory() {
        //        String storyKey = "GAM-1679";
        //        List<String> issueDesc = new
        // JiraApiServices().getAllIssueDescriptionOfStory(storyKey);
        //        issueDesc.stream().forEach(obj -> System.out.println(obj));
    }
}
