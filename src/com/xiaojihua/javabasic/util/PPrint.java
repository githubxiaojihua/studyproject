package com.xiaojihua.javabasic.util;

import java.util.Arrays;
import java.util.Collection;
import static com.xiaojihua.javabasic.util.Print.*;

public class PPrint {
    public static <C> String format(Collection<C> c){
        if(c.size() == 0){
            return "[]";
        }

        StringBuilder formatStrB = new StringBuilder("[");
        for(Object o : c){
            if(c.size() != 1){
                formatStrB.append("\n");
            }
            formatStrB.append(o);
        }
        formatStrB.append("\n").append("]");

        return formatStrB.toString();
    }

    public static void pprint(Collection<?> c){
        print(format(c));
    }

    public static void pprint(Object[] c){
        print(format(Arrays.asList(c)));
    }
}
