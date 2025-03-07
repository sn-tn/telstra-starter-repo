package au.com.telstra.simcardactivator;

/**
 * Structure that represents a SIM Card Activation Request. Structure contains SIM ICCID and
 * client email address. ICCID and email can be retrieved, but not modified.
 */
public class ActivationRequest {
  private final String iccid;
  private final String customerEmail;

  /**
   * Creates a new ActivationRequest using a given ICCID and customer email address.
   *
   * @param iccid unique SIM ICCID of SIM Card to be activated.
   * @param customerEmail email address of customer.
   */
  public ActivationRequest(String iccid, String customerEmail) {
    this.iccid = iccid;
    this.customerEmail = customerEmail;
  }

  public String getIccid() {
    return iccid;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }
}
