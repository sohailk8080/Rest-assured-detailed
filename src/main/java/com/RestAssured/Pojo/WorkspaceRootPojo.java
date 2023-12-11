package com.RestAssured.Pojo;

public class WorkspaceRootPojo {

    WorkspacePojo workspace;

    public WorkspaceRootPojo() {
    }

    public WorkspaceRootPojo(WorkspacePojo workspace) {
        this.workspace = workspace;
    }

    public WorkspacePojo getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspacePojo workspace) {
        this.workspace = workspace;
    }

}
