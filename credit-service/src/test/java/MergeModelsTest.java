import com.microservices.creditservice.models.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class MergeModelsTest {


    @Test
    public void setFinalListTest() {

        Credit credit = new Credit();
        credit.setId(1);
        credit.setCreditName("Test12" + credit.getId());

        Product product = new Product();
        product.setCreditId(1);
        product.setProductName("productName" + credit.getId());
        product.setProductValue(500 + credit.getId());


        Customer customer = new Customer();
        customer.setCreditId(credit.getId());
        customer.setPesel("8757421" + credit.getId());
        customer.setFirstName("FirstName" + credit.getId());
        customer.setSurName("SurName" + credit.getId());

        CreditList creditList = new CreditList.Builder()
                .credits(Arrays.asList(credit))
                .build();

        ProductList productList = new ProductList();
        productList.setProducts(Arrays.asList(product));

        CustomerList customerList = new CustomerList();
        customerList.setCustomers(Arrays.asList(customer));

        List<CreditOutputForm> form = MergeModels.setFinalList(creditList, productList, customerList);


        Assert.assertEquals(form.size(),1);
        Assert.assertEquals(form.get(0).getProductName(), "productName1");
        Assert.assertEquals(form.get(0).getFirstName(), "FirstName1");
    }
}