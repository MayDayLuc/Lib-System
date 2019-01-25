package controller.utils;

import java.io.Serializable;

public abstract class ChildController extends BaseController {
    protected Parental parentController;

    public void setParentController(Parental parentController) {
        this.parentController = parentController;
    }

    public abstract void setEdit(Serializable bean);
}
