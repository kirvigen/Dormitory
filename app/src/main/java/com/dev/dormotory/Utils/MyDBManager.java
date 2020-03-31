package com.dev.dormotory.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;
import android.util.LogPrinter;
import android.widget.ArrayAdapter;

import com.dev.dormotory.Obj.Category;
import com.dev.dormotory.Obj.Point;
import com.dev.dormotory.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
//Данные и работа с ними
public class MyDBManager extends SQLiteOpenHelper {
    static public String databaseName = "Vokrug.db";
     String[] NAMETABLES = new String[]{"Apteky","Gosuslugi","MagazynByta","Medical",
            "Poest","Producty","Razvlecheniya","Sport","University","Uslugi"};
     public static  String[] NAMES = new String[]{"Аптеки","Гос. учереждения","Магазины быта","Медицина","Поесть",
     "Продукты","Развлечения","Спорт","Университет","Услуги"};
    public static String[] COLORS = new String[]{"#78FF00","#0028C0","#ACA7A5","#DD6DFF","#FF0000",
            "#FF3636","#961280","#128496","#6574FF","#31D54D"};
    public static int[] icons_drawable = new int[]{R.drawable.ic_apteka,R.drawable.ic_gosuslugi,R.drawable.ic_hoz_tovari,
    R.drawable.ic_medicine,R.drawable.ic_eat,R.drawable.ic_product,R.drawable.ic_razvlechenie,R.drawable.ic_sport,R.drawable.ic_university,
    R.drawable.ic_uslugi};

    public MyDBManager(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, databaseName, factory, version);
    }

    public List<Category> getCategorys(){
        List<Category> categories = new ArrayList<>();
        for(int i=0; i<COLORS.length; i++){
            Category category = new Category();
            category.setName(NAMES[i]);
            category.setColor_id(COLORS[i]);
            category.setDrawable_id(icons_drawable[i]);
            List<Point> points = loadPoints(NAMETABLES[i]);
            String podtype = "";
            int id_podtype = 0;
            List<Category> categories1 = new ArrayList<>();
            if(!points.get(0).getPodtype().equals("")){
                for(int j = 0;j<points.size(); j++){
                    Point item = points.get(j);
                    if(j!=0){
                        if(item.getPodtype().equals(podtype)){
                            List<Point> pointList = categories1.get(id_podtype).getPoints();
                            pointList.add(item);
                            categories1.get(id_podtype).setPoints(pointList);
                        }else{
                            podtype = item.getPodtype();
                            id_podtype = categories1.size();
                            List<Point> points1 = new ArrayList<>();
                            points1.add(item);
                            Category podTypeCategory = new Category();
                            podTypeCategory.setDrawable_id(category.getDrawable_id());
                            podTypeCategory.setColor_id(category.getColor_id());
                            podTypeCategory.setName(item.getPodtype());
                            podTypeCategory.setPoints(points1);
                            categories1.add(podTypeCategory);
                        }
                    }else{
                        podtype = item.getPodtype();
                        List<Point> points1 = new ArrayList<>();
                        points1.add(item);
                        Category podTypeCategory = new Category();
                        podTypeCategory.setName(item.getPodtype());
                        podTypeCategory.setDrawable_id(category.getDrawable_id());
                        podTypeCategory.setColor_id(category.getColor_id());
                        podTypeCategory.setPoints(points1);
                        categories1.add(podTypeCategory);
                    }
                }
            }else{
                category.setPoints(points);
            }
            category.setCategories(categories1);
            categories.add(category);
        }

        return categories;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i=0;i<NAMETABLES.length;i++) {
            String table1 = "CREATE TABLE `" + NAMETABLES[i] + "` (\n" +
                    "  `id` int(2) DEFAULT NULL,\n" +
                    "  `lat` varchar(13) DEFAULT NULL,\n" +
                    "  `lng` varchar(13) DEFAULT NULL,\n" +
                    "  `type` TEXT DEFAULT NULL,\n" +
                    "  `podtype` TEXT DEFAULT NULL,\n" +
                    "  `nameO` TEXT DEFAULT NULL,\n" +
                    "  `Address` TEXT DEFAULT NULL,\n" +
                    "  `comment` TEXT DEFAULT NULL\n" +
                    ")";
            db.execSQL(table1);
        }
        Log.d(TAG, "onCreate: Таблицы созданы");
        String sqlCreate1 = "INSERT INTO "+NAMETABLES[0]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.945747', '30.483548', 'Аптеки', '', 'Озерки', 'ул. Осипенко 2', 'круглосуточная'),\n" +
                "(2, '59.944373', '30.47929', 'Аптеки', '', 'Вита', 'пр. Индустриальный 19', ''),\n" +
                "(3, '59.938604', '30.477655', 'Аптеки', '', 'Озерки', 'пр. Индустриальный 11 к. 1', ''),\n" +
                "(4, '59.945747', '30.483548', 'Аптеки', '', 'Живика', 'ул. Осипенко 2', ''),\n" +
                "(5, '59.945594', '30.486296', 'Аптеки', '', 'Первая помощь', 'пр. Косыгина 26', '')\n";
        db.execSQL(sqlCreate1);
        String sqlCreate2 = "INSERT INTO "+NAMETABLES[1]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.934006', '30.409131', 'Гос. учереждения', '', 'Военкомат', 'Республиканская 16', ''),\n" +
                "(2, '59.938847', '30.485802', 'Гос. учереждения', '', 'МФЦ', 'пр. Наставников 6 к. 2', ''),\n" +
                "(3, '59.937752', '30.472121', 'Гос. учереждения', '', 'Отдел полиции №13', 'ул. Передовиков 3', ''),\n" +
                "(4, '59.953903', '30.41471', 'Гос. учереждения', '', 'Налоговая', 'пр. Среднеохтинский 34/12', ''),\n" +
                "(5, '59.937752', '30.472121', 'Гос. учереждения', '', 'Паспортный стол', 'ул. Передовиков 3', '')";
        db.execSQL(sqlCreate2);
        String sqlCreate3 = "INSERT INTO "+NAMETABLES[2]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.944431', '30.48335', 'Магазины быта', 'Строительный магазин', 'Народный', 'Косыгина 21', 'В хозяйственных целях норм' ),\n" +
                "(2, '59.946905', '30.457883', 'Магазины быта', 'Строительный магазин', 'Максидом', 'ул. Передовиков 18 к.2', ''),\n" +
                "(3, '59.894075', '30.514755', 'Магазины быта', 'Строительный магазин', 'Икея', 'г. Кудрово, Мурманское шоссе 12 км 1', 'Можно проехать на 153 маршрутке'),\n" +
                "(4, '59.946175', '30.473981', 'Магазины быта', 'Одежда и обувь', 'Июнь', 'пр. Индустриальный 24', ''),\n" +
                "(5, '59.933023', '30.437635', 'Магазины быта', 'Одежда и обувь', 'Заневский каскад', 'пр. Заневский 67 к. 2', '' ),\n" +
                "(6, '59.894075', '30.514755', 'Магазины быта', 'Одежда и обувь', 'Мега', 'г. Кудрово, Мурманское шоссе 12 км 1', ''),\n" +
                "(7, '59.933663', '30.439', 'Магазины быта', 'ХозМаг', 'Домовой', 'пр. Заневский 71 к. 2', ''),\n" +
                "(8, '59.947672', '30.490635', 'Магазины быта', 'ХозМаг', 'Улыбка радуги', 'пр. Наставников 27', '' ),\n" +
                "(9, '59.945806', '30.481751', 'Магазины быта', 'концелярский', 'АзъБука', 'пр. Косыгина 24 к. 2', '' ),\n" +
                "(10, '59.946175', '30.473981', 'Магазины быта', 'книжный', 'Буквоед', 'пр. Индустриальный 24', '')";
        db.execSQL(sqlCreate3);
        String sqlCreate4 = "INSERT INTO "+NAMETABLES[3]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.941367', '30.482479', 'Медицина', 'Поликлиника', 'Поликлиника №120', 'ул. Ленская 4', ''),\n" +
                "(2, '59.946396', '30.462132', 'Медицина', 'Поликлиника', 'Дет. Поликлиника №68', 'ул. Передовиков 21', ''),\n" +
                "(3, '59.870863', '30.308673', 'Медицина', 'Поликлиника', 'Студ. Поликлиника №75', 'ул. Кузнецовская 9', ''),\n" +
                "(4, '59.9444', '30.489396', 'Медицина', 'Стоматология', 'Стоматологическая поликлиника №32', 'пр. Наставников 22', '')";
        db.execSQL(sqlCreate4);
        String sqlCreate5 = "INSERT INTO "+NAMETABLES[4]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.938135', '30.47734', 'Поесть', 'Шаверма', 'Шаверма', 'пр. Индустриальный 9а', ''),\n" +
                "(2, '59.941367', '30.467746', 'Поесть', 'Столовая', 'Столовая ГУАП', 'пр. Передовиков 13', ''),\n" +
                "(3, '59.945513', '30.482371', 'Поесть', 'Пиццерия', 'Ямм Пицца', 'пр. Косыгина 24 к. 1', ''),\n" +
                "(4, '59.948194', '30.479784', 'Поесть', 'Пиццерия', 'Папа Джонс', 'пр. Энтузиастов 33 к. 1', ''),\n" +
                "(5, '59.945806', '30.481751', 'Поесть', 'Пиццерия', 'Pizza Hut', 'пр. Косыгина 24 к. 2', ''),\n" +
                "(6, '59.933023', '30.437635', 'Поесть', 'Фудкорт', 'Заневский каскад', 'пр. Заневский 67 к. 2', ''),\n" +
                "(7, '59.946175', '30.473981', 'Поесть', 'Фудкорт', 'Июнь', 'пр. Индустриальный 24', ''),\n" +
                "(8, '59.947946', '30.4739', 'Поесть', 'Кафе', 'Токио-Сити', 'пр. Индустриальный 26/24', ''),\n" +
                "(9, '59.933023', '30.437635', 'Поесть', 'Кафе', 'Евразия', 'пр. Заневский 67 к. 2', ''),\n" +
                "(10, '59.948194', '30.479784', 'Поесть', 'Кафе', 'Хачапури и Хинкали', 'пр. Энтузиастов 33 к. 1', '')";
        db.execSQL(sqlCreate5);
        String sqlCreate6 = "INSERT INTO "+NAMETABLES[5]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.942092', '30.469615', 'Продукты', '', 'Дикси', 'пр. Косыгина 7 к. 2', ''),\n" +
                "(2, '59.947023', '30.477466', 'Продукты', '', 'Окей', 'пр. Индустриальный 25', ''),\n" +
                "(3, '59.946175', '30.473981', 'Продукты', '', 'Карусель', 'пр. Индустриальный 24', ''),\n" +
                "(4, '59.933023', '30.437635', 'Продукты', '', 'Перекрёсток', 'пр. Заневский 67 к. 2', ''),\n" +
                "(5, '59.934483', '30.497543', 'Продукты', '', 'Лента', 'ул. Хасанская 17 к. 1', ''),\n" +
                "(6, '59.944431', '30.48335', 'Продукты', '', 'Народный', 'Косыгина 21 к. 1', 'Ходить в крайнем случае'),\n" +
                "(7, '59.938685', '30.474816', 'Продукты', '', 'Пятерочка', 'пр. Индустриальный 10 к. 1', ''),\n" +
                "(8, '59.933104', '30.440626', 'Продукты', '', 'Лента', 'пр. Заневский 71', ''),\n" +
                "(9, '59.941948', '30.478355', 'Продукты', '', 'Магнит', 'пр. Индустриальный 15', ''),\n" +
                "(10, '59.948834', '30.475463', 'Продукты', '', 'Ермолино', 'пр. Индустриальный 27', 'Дешево и сердито')";
        db.execSQL(sqlCreate6);
        String sqlCreate7 = "INSERT INTO "+NAMETABLES[6]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.946175', '30.473981', 'Развлечения', 'Боулинг/бильярд', 'Июнь', 'пр. Индустриальный 24', ''),\n" +
                "(2, '59.944706', '30.494462', 'Развлечения', 'Кальянная', 'ENDORPHIN', 'пр. Косыгина 27 к. 1', 'Для лиц старше 18 лет'),\n" +
                "(3, '59.946175', '30.473981', 'Развлечения', 'Кинотеатр', 'MORI CINEMA', 'пр. Индустриальный 24', ''),\n" +
                "(4, '59.933023', '30.437635', 'Развлечения', 'Кинотеатр', 'Формула Кино', 'пр. Заневский 67 к. 2', ''),\n" +
                "(5, '59.945373', '30.45888', 'Развлечения', 'Досуг', 'Молодёжный центр \\\"Квадрат\\\"', 'ул. Передовиков 16 к. 2', ''),\n" +
                "(6, '59.950956', '30.473693', 'Развлечения', 'Батутный центр', 'Fun Jump', 'пр. Индустриальный 31', '')";
        db.execSQL(sqlCreate7);
        String sqlCreate8 = "INSERT INTO "+NAMETABLES[7]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.941407', '30.466938', 'Спорт', 'Спортзал', 'Фитнес ГУАП', 'ул. Передовиков 13 к. 2', ''),\n" +
                "(2, '59.945373', '30.45888', 'Спорт', 'Спортзал', 'Квадрат', 'ул. Передовиков 16 к. 2', 'бесплатно'),\n" +
                "(3, '59.937918', '30.482901', 'Спорт', 'Спортзал', 'Fitness House', 'ул. Хасанская 10 к. 2', 'с бассейном'),\n" +
                "(4, '59.946175', '30.473981', 'Спорт', 'Спортзал', 'Extra Sport', 'пр. Индустриальный 24', ''),\n" +
                "(5, '59.945373', '30.45888', 'Спорт', 'Кольцо', 'Баскетбольное кольцо', 'ул. Передовиков 16 к. 2', 'во дворе'),\n" +
                "(6, '59.93887', '30.473585', 'Спорт', 'Поле', 'Футбольное поле', 'пр. Индустриальный 10 к. 2', '')";
        db.execSQL(sqlCreate8);
        String sqlCreate9 = "INSERT INTO "+NAMETABLES[8]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.929556', '30.296671', 'Университет', '', 'Главный', 'ул. Большая морская 67', ''),\n" +
                "(2, '59.857364', '30.327771', 'Университет', '', 'Для 1-2 курса', 'ул. Гастелло 15', ''),\n" +
                "(3, '59.855814', '30.330475', 'Университет', '', 'Для юристов и экономистов', 'ул. Ленсовета 14', '')";
        db.execSQL(sqlCreate9);
        String sqlCreate10 = "INSERT INTO "+NAMETABLES[9]+" (`id`, `lat`, `lng`, `type`, `podtype`, `nameO`, `Address`, `comment`) VALUES\n" +
                "(1, '59.94179', '30.468429', 'Услуги', 'Парикмахерская', 'Альбина', 'пр. Косыгина 7 к. 1', ''),\n" +
                "(2, '59.947023', '30.477466', 'Услуги', 'Химчистка', 'Apetta', 'пр. Индустриальный 25', ''),\n" +
                "(3, '59.946905', '30.457883', 'Услуги', 'Ключи', 'Максидом', 'ул. Передовиков 18 к. 2', ''),\n" +
                "(4, '59.938685', '30.474816', 'Услуги', 'Ателье', 'Пятерочка', 'пр. Индустриальный 10 к. 1', ''),\n" +
                "(5, '59.94083', '30.456176', 'Услуги', 'Ремонт телефонов', 'Imaster', 'Косыгина 4а', ''),\n" +
                "(6, '59.914033', '30.297857', 'Услуги', 'Автошкола', 'ФАРА', 'пр. Лермонтовский 44', ''),\n" +
                "(7, '59.945806', '30.481751', 'Услуги', 'Фотоцентр', 'Printsburg', 'пр. Косыгина 24 к. 2', '')";
        db.execSQL(sqlCreate10);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Point> loadPoints(String table) {
        List<Point> points= new ArrayList<>();
        String query = "Select * FROM " + table +" WHERE id";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {

                Point point = new Point();
//                Log.e(TAG, "loadPoints: string "+cursor.getString(1)+" "+cursor.getString(2)+" double "+cursor.getDouble(1)+" "+cursor.getDouble(2) );
                point.setLat(Double.valueOf(cursor.getString(1)));
                point.setLng(Double.valueOf(cursor.getString(2)));
                point.setPodtype(cursor.getString(4));
                point.setName(cursor.getString(5));
                point.setAdress(cursor.getString(6));
                point.setComment(cursor.getString(7));
                points.add( point );

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return points;
    }

}
