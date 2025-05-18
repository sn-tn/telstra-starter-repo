package au.com.telstra.simcardactivator.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActivationRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String iccid;
  private String customerEmail;
  private boolean active;

  protected ActivationRecord() {}

  public ActivationRecord(final String iccid, final String customerEmail, final boolean active) {
    this.iccid = iccid;
    this.customerEmail = customerEmail;
    this.active = active;
  }

  @Override
  public String toString() {
    return String.format("Activation Record[id=%d, iccid=%s, customerEmail=%s, active=%s",
            id, iccid, customerEmail, active);
  }

  public long getId() {
    return id;
  }

  public String getIccid() {
    return iccid;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public boolean isActive() {
    return active;
  }
}
