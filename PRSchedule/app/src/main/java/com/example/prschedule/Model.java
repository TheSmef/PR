package com.example.prschedule;

public class Model {
    private String title;
    private String nameZnam1;
    private  String nameDel1;
    private String nameZnam2;
    private  String nameDel2;
    private String nameZnam3;
    private  String nameDel3;
    private String nameZnam4;
    private  String nameDel4;
    private String nameZnam5;
    private  String nameDel5;

    public Model(String title, String nameZnam1, String nameDel1, String nameZnam2, String nameDel2, String nameZnam3, String nameDel3, String nameZnam4, String nameDel4, String nameZnam5, String nameDel5){
        this.title = title;
        this.nameZnam1 = nameZnam1;
        if (!nameDel1.equals("none")) {
            this.nameDel1 = nameDel1;
        }
        this.nameZnam2 = nameZnam2;
        if (!nameDel2.equals("none")){
            this.nameDel2 = nameDel2;
        }
        this.nameZnam3 = nameZnam3;
        if (!nameDel3.equals("none")){
            this.nameDel3 = nameDel3;
        }
        this.nameZnam4 = nameZnam4;
        if (!nameDel4.equals("none")){
            this.nameDel4 = nameDel4;
        }
        this.nameZnam5 = nameZnam5;
        if (!nameDel5.equals("none")){
            this.nameDel5 = nameDel5;
        }
    }

    public String getNameZnam1() {return nameZnam1;}
    public  String getNameDel1() {return nameDel1;}
    public String getNameZnam2() {return nameZnam2;}
    public  String getNameDel2() {return nameDel2;}
    public String getNameZnam3() {return nameZnam3;}
    public  String getNameDel3() {return nameDel3;}
    public String getNameZnam4() {return nameZnam4;}
    public  String getNameDel4() {return nameDel4;}
    public String getNameZnam5() {return nameZnam5;}
    public  String getNameDel5() {return nameDel5;}
    public String getTitle() {return  title;}

}
