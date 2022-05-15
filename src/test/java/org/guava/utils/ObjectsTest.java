package org.guava.utils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.util.Calendar;

/**
 * 用於簡化 java hashCode toString compareTo equals方法
 * 但現多都使用自動生成 因此較無太大作用
 */
public class ObjectsTest {
    static class Guava implements  Comparable<Guava>{
        private final String manufacturer;
        private final String version;
        private final Calendar releaseDate;

        public Guava(String manufacturer, String version, Calendar releaseDate) {
            this.manufacturer = manufacturer;
            this.version = version;
            this.releaseDate = releaseDate;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(manufacturer,version,releaseDate);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Guava guava = (Guava) obj;

            return Objects.equal(this.manufacturer,guava.manufacturer)
                    && Objects.equal(this.version,guava.version)
                    && Objects.equal(this.releaseDate,guava.releaseDate);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("manufacturer",this.manufacturer)
                    .add("version",this.version)
                    .add("releaseDate",this.releaseDate)
                    .toString();
        }

        @Override
        public int compareTo(Guava o) {
            return ComparisonChain.start().compare(this.manufacturer,o.manufacturer)
                    .compare(this.releaseDate,o.releaseDate)
                    .compare(this.version,o.version)
                    .result();
        }
    }

    public static void main(String[] args) {
        final Guava guava = new Guava("Google","31.1",Calendar.getInstance());
        System.out.println(guava);
        System.out.println(guava.hashCode());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        final Guava guava1 = new Guava("Google","31.1",calendar);
        System.out.println(guava.compareTo(guava1));
    }
}
