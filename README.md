# 📱 Java Engineering Calculator
> Java Swing을 이용해 구현한 공학용 계산기 프로젝트입니다.

---
### 🛠 기술 스택
- **Language**: Java 17
- **IDE**: Eclipse (Initial), VS Code (Current)

### ✨ 주요 기능
- [x] 기본 사칙연산
- [x] 공학용 함수 (sin, cos, log 등)
- [x] 계산 기록 저장 및 불러오기

### 🛠 주요 구성 요소

### 1. 프로그램 실행 (Main)
* **CalcMain.java**: 본 공학용 계산기의 시작점으로, `ScientificCalculator` 객체를 생성하며 GUI 창을 화면에 띄우는 역할을 합니다.

### 2. 컨트롤러 (Controller)
* **ScientificCalculator.java**: 메인 GUI 컨트롤러입니다. 다양한 UI 컴포넌트를 통합하고 사용자의 입력을 처리하는 중추적인 역할을 수행합니다.

### 3. 연산 로직 (Logic)
* **CalculatorLogic.java**: 수학적 연산을 전담합니다. `ScientificCalculator`로부터 연산자와 피연산자를 전달받아 실제 결과값을 산출하며, Java의 `Math` 라이브러리를 활용해 복잡한 수식을 계산합니다.

### 4. GUI 컴포넌트 (Panels)
각 패널은 독립적인 기능을 수행하도록 분리되어 설계되었습니다.
* **Basicpanel.java**: 일반 계산기 기능. 숫자, 사칙연산, 초기화 버튼을 포함하며 중심부에 위치합니다.
* **Scipanel.java**: 공학용 계산 기능. 삼각함수, 로그, 거듭제곱 등 특수 연산 버튼을 모아놓은 패널로 좌측에 위치합니다.
* **Historypanel.java**: 계산 기록 패널. 모든 계산 과정과 결과를 리스트로 기록하며, 저장된 데이터를 다시 불러오는 인터페이스 역할을 합니다.
* **Dispanel.java**: 입력 및 출력 패널. 최상단에 위치하여 사용자의 입력과 결과값을 실시간으로 보여줍니다.
* **Imagepanel.java**: 배경 이미지 패널. `JPanel`을 상속받아 패널 전체에 이미지를 그리는 시각적 기능을 수행합니다.

### 5. 보조 기능 (Visualizer)
* **GraphWindow.java**: 메인 계산기에서 전달받은 숫자(진폭/계수)를 활용하여 삼각함수(sin, cos, tan)의 그래프를 좌표평면에 그리는 보조 프레임입니다.

### 📄 문서
- [프로젝트 상세 설명서 보기](./report.pdf)