package utilities;

import io.qameta.allure.Attachment;

public class AllureAttachmentManager {

    @Attachment(
            value = "Failure Screenshot",
            type = "image/png")
    public static byte[] attachScreenshot(
            byte[] screenshot) {

        return screenshot;
    }
}