#Chapter 1. 들어가기

* 변경해야 할 곳이 많음. 어디부터 변경해야 할지 모르겠음.
* 소프트웨어를 만드는 개발자가 [변화에 대응할 수 있는 구조를 설계]하는 데에 미숙함

#### 1. 지저분해지는 코드

~~~ java
[리스트 1.1 최초의 코드]
public class Application implements OnClickListener {
    
    private Menu menu1 = new Menu("menu1");
    private Menu menu2 = new Menu("menu2");
    private Button button1 = new Button("button1");

    private String currentMenu = null;

    public Application() {
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    public void clicked(Component eventSource) {
        if (eventSource.getId().equals("menu1")) {
            changeUIToMenu1();
        } else if (eventSource.getId().equals("menu2")) {
            changeUIToMenu2();
        } else if (eventSource.getId().equals("button1")) {
            if (currentMenu == null)
                return;
            if (currentMenu.equals("menu1"))
                processButton1WhenMenu1();
            else if (currentMenu.equals("menu2"))
                processButton1WhenMenu2();
        }
    }

    private void changeUIToMenu1() {
        currentMenu = "menu1";
        System.out.println("메뉴1 화면으로 전환");
    }
    
    private void changeUIToMenu2() {
        currentMenu = "menu2";
        System.out.println("메뉴2 화면으로 전환");
    }

    private void processButton1WhenMenu1() {
        System.out.println("메뉴1 화면의 버튼1 처리");
    }

    private void processButton1WhenMenu2() {
        System.out.println("메뉴2 화면의 버튼1 처리");
    }
}
~~~

* 문제점 : 메뉴, 버튼을 계속 추가할 수록 작성해야 할 if-else 블록이 많아진다.
    * 코드를 복잡하게 만든다.
    * 개발자가 코드를 추가, 수정하는 데 오랜 시간이 걸린다.

#### 2. 수정하기 좋은 구조를 가진 코드

* 객체 지향에서는 추상화, 다형성을 이용해서 변화되는 부분을 관리한다.