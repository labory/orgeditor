package com.carcasser.orgeditor.client.application;

import com.carcasser.orgeditor.client.resources.AppMessages;
import com.carcasser.orgeditor.shared.Organization;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import java.util.Date;
import java.util.List;

/**
 * Organization view.
 */
public class OrganizationsView extends ViewWithUiHandlers<OrganizationsUiHandlers> implements OrganizationsPresenter.MyView {
    interface Binder extends UiBinder<Widget, OrganizationsView> {
    }

    @UiField
    ListBox localeBox;

    @UiField(provided = true)
    CellTable<Organization> orgTable;

    @UiField
    Button create;

    private final AppMessages messages;

    @Inject
    OrganizationsView(Binder uiBinder, AppMessages messages) {
        this.messages = messages;

        initOrgTable();
        initWidget(uiBinder.createAndBindUi(this));
        initializeLocaleBox();
    }

    @Override
    public void setError(String errorText) {
    }

    @UiHandler("create")
    void onSend(ClickEvent event) {
        getUiHandlers().onCreate();
    }

    public void displayOrganizations(List<Organization> organizations) {
        orgTable.setRowData(0, organizations);
        orgTable.setRowCount(organizations.size());
    }

    private void initOrgTable() {
        orgTable = new CellTable<>();
        orgTable.setSelectionModel(new NoSelectionModel<Organization>());

        Column<Organization, Number> idColumn = new Column<Organization, Number>(new NumberCell()) {
            public Long getValue(Organization org) {
                return org.getId();
            }
        };
        Column<Organization, String> nameColumn = new Column<Organization, String>(new TextCell()) {
            public String getValue(Organization org) {
                return org.getName();
            }
        };
        Column<Organization, String> addressColumn = new Column<Organization, String>(new TextCell()) {
            public String getValue(Organization org) {
                return org.getAddress();
            }
        };
        Column<Organization, String> descColumn = new Column<Organization, String>(new TextCell()) {
            public String getValue(Organization org) {
                return org.getDescription();
            }
        };

        orgTable.addColumn(idColumn, messages.columnOrgId());
        orgTable.addColumn(nameColumn, messages.columnOrgName());
        orgTable.addColumn(addressColumn, messages.columnOrgAddress());
        orgTable.addColumn(descColumn, messages.columnOrgDesc());
        orgTable.setColumnWidth(idColumn, 50, Style.Unit.PX);

        Cell<Organization> editCell = new ActionCell<Organization>(messages.buttonEdit(), new ActionCell.Delegate<Organization>() {
            public void execute(Organization org) {
                getUiHandlers().onEdit(org);
            }
        });

        Cell<Organization> deleteCell = new ActionCell<Organization>(messages.buttonDelete(), new ActionCell.Delegate<Organization>() {
            public void execute(Organization org) {
                Boolean confirm = Window.confirm(messages.deleteConfirm(org.getName()));
                if (confirm) {
                    getUiHandlers().onDelete(org);
                }
            }
        });

        Cell<Organization> detailsCell = new ActionCell<Organization>(messages.buttonDetails(), new ActionCell.Delegate<Organization>() {
            public void execute(Organization org) {
                getUiHandlers().onDetails(org);
            }
        });

        IdentityColumn<Organization> editColumn = new IdentityColumn<>(editCell);
        IdentityColumn<Organization> deleteColumn = new IdentityColumn<>(deleteCell);
        IdentityColumn<Organization> detailsColumn = new IdentityColumn<>(detailsCell);

        orgTable.addColumn(editColumn, messages.columnOrgEdit());
        orgTable.addColumn(deleteColumn, messages.columnOrgDelete());
        orgTable.addColumn(detailsColumn, messages.columnOrgDetails());
    }

    /**
     * Initialize the {@link ListBox} used for locale selection.
     */
    private void initializeLocaleBox() {
        final String cookieName = LocaleInfo.getLocaleCookieName();
        final String queryParam = LocaleInfo.getLocaleQueryParam();
        String currentLocale = LocaleInfo.getCurrentLocale().getLocaleName();
        if (currentLocale.equals("default")) {
            currentLocale = "en";
        }
        String[] localeNames = new String[] {"en", "ru"};
        for (String localeName : localeNames) {
            if (!localeName.equals("default")) {
                String nativeName = LocaleInfo.getLocaleNativeDisplayName(localeName);
                localeBox.addItem(nativeName, localeName);
                if (localeName.equals(currentLocale)) {
                    localeBox.setSelectedIndex(localeBox.getItemCount() - 1);
                }
            }
        }
        localeBox.addChangeHandler(new ChangeHandler() {
            @SuppressWarnings("deprecation")
            public void onChange(ChangeEvent event) {
                String localeName = localeBox.getValue(localeBox.getSelectedIndex());
                if (cookieName != null) {
                    Date expires = new Date();
                    expires.setYear(expires.getYear() + 1);
                    Cookies.setCookie(cookieName, localeName, expires);
                }
                if (queryParam != null) {
                    UrlBuilder builder = Window.Location.createUrlBuilder().setParameter(
                            queryParam, localeName);
                    Window.Location.replace(builder.buildString());
                } else {
                    Window.Location.reload();
                }
            }
        });
    }
}

