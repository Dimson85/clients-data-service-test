# ProductApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**updateCard**](ProductApi.md#updateCard) | **PUT** /card | 


<a name="updateCard"></a>
# **updateCard**
> Card updateCard(cardDto)



Обновление данных карты

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProductApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ProductApi apiInstance = new ProductApi(defaultClient);
    Card cardDto = new Card(); // Card | Данные карты
    try {
      Card result = apiInstance.updateCard(cardDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductApi#updateCard");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **cardDto** | [**Card**](Card.md)| Данные карты |

### Return type

[**Card**](Card.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | CREATED |  -  |
**400** | Bad request |  -  |
**403** | Forbidden |  -  |
**500** | Internal server error |  -  |

