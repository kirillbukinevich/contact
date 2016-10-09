package logic.commands.editcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.entity.ContactPhone;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 01.10.2016.
 */
public class EditPhoneCommand extends UpdateCommand {
    public String execute(HttpServletRequest request) {
        updatePhone(request);
        super.fillAllParameters(request);

        return getProperty("path.page.edit");
    }


    public void updatePhone(HttpServletRequest request) {
        ContactPhone editPhone = getEditPhone(request);
        ContactPhone newPhone = null;
        if (editPhone.isSaved()) {
            editPhone.setIsUpdated(true);
            newPhone = editPhone.clone();
            newPhone.setIsSaved(false);
            editPhone.setIsDeleted(true);
        } else {
            newPhone = editPhone;
        }

        ArrayList<ContactPhone> phoneList = getEmployeeFromSession(request).getPhoneList();
        if (newPhone != editPhone) {
            phoneList.add(getPhoneFromJSP(request, newPhone));
        } else {
            phoneList.set(phoneList.indexOf(editPhone), getPhoneFromJSP(request, newPhone));
        }
    }

    public ContactPhone getEditPhone(HttpServletRequest request) {
        ContactPhone editPhone = (ContactPhone) request.getSession().getAttribute("edit_phone");
        request.getSession().setAttribute("edit_phone", null);
        return editPhone;
    }


    public ContactPhone getPhoneFromJSP(HttpServletRequest request, ContactPhone phone) {
        phone.setCodeCountry(Integer.valueOf(request.getParameter("code_country")));
        phone.setCodeOperator(Integer.valueOf(request.getParameter("code_operator")));
        phone.setNumber(Integer.parseInt(request.getParameter("phone_number")));
        phone.setType(request.getParameter("phone_type"));
        phone.setComment(request.getParameter("comment"));
        return phone;
    }
}
