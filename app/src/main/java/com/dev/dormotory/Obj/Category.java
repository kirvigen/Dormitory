package com.dev.dormotory.Obj;

import java.util.ArrayList;
import java.util.List;

public class Category {
        private String name;
        private int drawable_id;
        private String color_id;
        private List<Category> categories = null;
        private List<Point> points = null;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getDrawable_id() {
                return drawable_id;
        }

        public void setDrawable_id(int drawable_id) {
                this.drawable_id = drawable_id;
        }

        public String getColor_id() {
                return color_id;
        }

        public void setColor_id(String color_id) {
                this.color_id = color_id;
        }

        public List<Category> getCategories() {
                return categories;
        }

        public void setCategories(List<Category> categories) {
                this.categories = categories;
        }

        public List<Point> getPoints() {
                return points;
        }

        public void setPoints(List<Point> points) {
                this.points = points;
        }
}
