package com.carcasser.orgeditor.client.resources;

/**
 * Interface to represent the messages contained in resource bundle: AppMessages.properties'.
 */
public interface AppMessages extends com.google.gwt.i18n.client.Messages {

    String greeting(String p0);

    String deleteConfirm(String p0);

    String organizationsTitle();

    String columnOrgId();

    String columnOrgName();

    String columnOrgAddress();

    String columnOrgDesc();

    String columnOrgEdit();

    String columnOrgDelete();

    String columnOrgDetails();

    String buttonEdit();

    String buttonDelete();

    String buttonDetails();

    String buttonAdd();

    String buttonSave();

    String buttonClose();

    String buttonBack();

    String editorOrgName();

    String editorOrgAddress();

    String editorOrgDesc();

    String actionCreateOrg();

    String actionEditOrg();

    String detailsAddress();

    String detailsDesc();
}
