package by.it.academy.gittestmodule;

import java.io.Serializable;

public class GitTestClass2 implements Serializable {
    private String repoName;
    private String branchName;

    public GitTestClass2(String repoName, String branchName) {
        this.repoName = repoName;
        this.branchName = branchName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public boolean checkRepo() {
        return true;
    }
}
