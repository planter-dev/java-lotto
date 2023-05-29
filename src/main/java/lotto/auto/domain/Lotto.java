package lotto.auto.domain;

import lotto.auto.vo.EntireNumber;
import lotto.auto.vo.WinNumber;

import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumber;

    public Lotto() {
        this.lottoNumber = new EntireNumber().shuffleAndPickNumber();
    }

    public List<Integer> getLottoNumbers() {
        return this.lottoNumber;
    }

    public Win checkWin(WinNumber winNumber) {
        int matchNumber = 0;
        for (Integer number : winNumber.getWinNumbers()) {
            matchNumber += isMatch(number);
        }
        boolean isMatchBonusNumber = isMatchBonusBall(winNumber.getBonusNumber());
        return Win.getWin(matchNumber, isMatchBonusNumber);
    };

    private int isMatch(Integer win) {
        if(lottoNumber.contains(win)) {
            return 1;
        };
        return 0;
    }

    private boolean isMatchBonusBall(int bonusNumber) {
        return lottoNumber.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(lottoNumber);
    }
}
