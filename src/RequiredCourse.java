public class RequiredCourse extends Course{
    private int credit;  // 学分

    public RequiredCourse(int i, String s) {
        this.id = i;
        this.name = s;
        type = 0;
    }

    public RequiredCourse(int id, String name, Teacher t, int num, int credit) {
        this(id, name);
        this.teacher = t;
        this.num_people = num;
        this.credit = credit;
    }

    public void show() {
        System.out.print(id + " " + name);
        System.out.println(" 必修 ");
        teacher.show();
        System.out.println(" " + num_people + " " + credit);
    }

    public String toString() {
        return super.toString() + " " + this.credit;
    }
}
