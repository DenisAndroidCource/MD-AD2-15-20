package by.it.academy.gittestmodule;

public class GitTestClass {
    private String repoName;
    private String branchName;

    public GitTestClass(String repoName, String branchName) {
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
}
