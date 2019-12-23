package de.daikol.tvsurvey.frontend.presenter;

import java.io.Serializable;

/**
 * Presenters as Navigator for each view.
 */
public class Presenters implements Serializable{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 7578879240418436823L;

    /**
     * loginPresenter.
     */
    LoginPresenter loginPresenter;

    /**
     * surveyPresenter.
     */
    SurveyPresenter surveyPresenter;

    /**
     * surveyEditPresenter.
     */
    SurveyEditPresenter surveyEditPresenter;

    /**
     * Getter for the member <code>surveyPresenter</code>.
     * 
     * @return Returns <code>surveyPresenter</code>.
     */
    public SurveyPresenter getSurveyPresenter() {
        return this.surveyPresenter;
    }

    /**
     * Setter for <code>surveyPresenter</code>.
     *
     * @param surveyPresenter
     *            New value used for <code>surveyPresenter</code>.
     */
    public void setSurveyPresenter(SurveyPresenter surveyPresenter) {
        this.surveyPresenter = surveyPresenter;
    }

    /**
     * Getter for the member <code>loginPresenter</code>.
     * 
     * @return Returns <code>loginPresenter</code>.
     */
    public LoginPresenter getLoginPresenter() {
        return this.loginPresenter;
    }

    /**
     * Setter for <code>loginPresenter</code>.
     *
     * @param loginPresenter
     *            New value used for <code>loginPresenter</code>.
     */
    public void setLoginPresenter(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    public SurveyEditPresenter getSurveyEditPresenter() {
        return surveyEditPresenter;
    }

    public void setSurveyEditPresenter(SurveyEditPresenter surveyEditPresenter) {
        this.surveyEditPresenter = surveyEditPresenter;
    }
}
