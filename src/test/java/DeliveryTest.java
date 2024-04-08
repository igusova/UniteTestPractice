import org.fpmi.Delivery;
import org.fpmi.Load;
import org.fpmi.Size;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DeliveryTest {

    @Mock
    private Delivery deliveryMock;


    @Tag("smoke")
    @DisplayName("Проверка, что не хрупкий груз можно перевозить на любые расстояния")
    @ParameterizedTest
    @ValueSource(ints = {10,30,40})
    void costNoFragileTest(int distance) {
        assertEquals(0.0, Delivery.getCostFragile(false, distance));
    }

    @Test
    @Tag("smoke")
    @DisplayName("Проверка стоимости в зависимости от хрупкости")
    void costFragileTest() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> Delivery.getCostFragile(true,45));
        assertEquals("Невозможно доставить груз", thrown.getMessage());
        assertEquals(300.0, Delivery.getCostFragile(true, 20));
    }


    @Test
    @Tag("smoke")
    @DisplayName("Проверка стоимости в зависимости от расстояния")
    void costDistanceTest() {
        assertEquals(50.0, Delivery.getCostDistance(1));
        assertEquals(100.0, Delivery.getCostDistance(8));
        assertEquals(200.0, Delivery.getCostDistance(25));
        assertEquals(300.0, Delivery.getCostDistance(30));
        assertEquals(300.0, Delivery.getCostDistance(40));
    }

    @Test
    @Tag("smoke")
    @DisplayName("Проверка стоимости в зависимости от габаритов")
    void costSizeTest() {

        Mockito.when(deliveryMock.getCostSize(Mockito.any())).thenReturn(500.0);

        Double actualCost = deliveryMock.getCostSize(Size.SMALL);

        assertEquals(500.0, actualCost);
    }

    @Test
    @Tag("regular")
    @DisplayName("Проверка расчета стоимости доставки")
    void testCostDelivery() {
        Delivery order1 = new Delivery(30,Size.BIG,false, Load.HIGH);
        Delivery order2 = new Delivery(15,Size.SMALL,true, Load.NORMAL);
        Delivery order3 = new Delivery(15,Size.SMALL,true, Load.EXTREMELY_HIGH);
        Delivery order4 = new Delivery(1,Size.BIG,true, Load.VERY_HIGH);


        assertEquals(600.0, Delivery.getCost(order1));
        assertEquals(600.0, Delivery.getCost(order2));
        assertEquals(960.0, Delivery.getCost(order3));
        assertEquals(770.0, Delivery.getCost(order4));

    }
}
