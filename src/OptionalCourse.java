public class OptionalCourse extends Course{
    private int maxStuNum;

    public OptionalCourse(int i, String s) {
        this.id = i;
        this.name = s;
        type = 1;
    }

    public OptionalCourse(int id, String name, Teacher t, int num, int maxNum) {
        this(id, name);
        this.teacher = t;
        this.num_people = num;
        this.maxStuNum = maxNum;
    }

    public void show() {
        System.out.print(id + " " + name);
        System.out.println(" 选修 ");
        teacher.show();
        System.out.println(" " + num_people + " " + maxStuNum);
    }

    public String toString() {
        return super.toString() + " " + this.maxStuNum;
    }
}
