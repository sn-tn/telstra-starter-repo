package au.com.telstra.simcardactivator;

import au.com.telstra.simcardactivator.database.ActivationRecord;
import au.com.telstra.simcardactivator.database.ActivationRecordRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SimCardController {

  private final ActivationRecordRepository repository;

  public SimCardController(ActivationRecordRepository repository) {
    this.repository = repository;
  }

  // Previous mapping for "/activate" post requests
  public void activateSuccess(@RequestBody ActivationRequest activationReq) {
    // stores url of SimCardActivator endpoint as string
    String url = "http://localhost:8444/actuate";

    // creates request JSON as a Map that contains ICCID
    Map<String, Object> request = new HashMap<>();
    request.put("iccid", activationReq.getIccid());
    // use RestTemplate to make request to SimCardActivator and return response as a Map
    RestTemplate restTemplate = new RestTemplate();
    Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);

    // print value of "success" in response
    System.out.println(response.get("success"));
  }

  @PostMapping(path = "/actuate")
  public void activate(@RequestBody ActivationRequest activationReq) {
    // stores url of SimCardActivator endpoint as string
    String url = "http://localhost:8444/actuate";

    // creates request JSON as a Map that contains ICCID
    Map<String, Object> request = new HashMap<>();
    request.put("iccid", activationReq.getIccid());

    // use RestTemplate to make request to SimCardActivator and return response as a Map
    RestTemplate restTemplate = new RestTemplate();
    Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);

    // make sure success is not null
    boolean success;
    try {
      success = (boolean) response.get("success");
    } catch (NullPointerException e) {
      System.out.println("The activation success could not be found.");
      return;
    }

    // add new record to repository
    repository.save(new ActivationRecord(activationReq.getIccid(),
            activationReq.getCustomerEmail(),
            success));
  }
}
