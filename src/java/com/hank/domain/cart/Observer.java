
package com.hank.domain.cart;

import com.hank.domain.item.Combo;
import java.util.Map;


public interface Observer {
    void update( Map<Combo, Integer> cartItems);
}
