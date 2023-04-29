package step1;

import step1.domain.Calculate;
import step1.domain.Number;
import step1.service.CalculateService;
import step1.view.InputView;
import step1.view.OutputView;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CalculateService calculateService = new CalculateService();

        // input 값 입력
        String inputValue = inputView.inputView();
        // split 로 나누기
        String[] splitStrings = calculateService.split(inputValue);
        // Number 만 뻬서 보기
        Number numbers = calculateService.sectionNumber(splitStrings);
        // 공식만 빼서 보기
        Calculate calculates = calculateService.sectionCalculate(splitStrings);
        // 계산
        int result = calculateService.calculate(numbers, calculates);
        // 프린트
        outputView.print(result);
    }
}
