package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String namesLine = Console.readLine();
        List<String> names = parseNames(namesLine);

        System.out.println("시도할 횟수는 몇 회인가요?");
        String attemptsLine = Console.readLine();
        int attempts = parseAttempts(attemptsLine);

        System.out.println();
        System.out.println("실행 결과");
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            positions.add(0);
        }

        for (int round = 0; round < attempts; round++) {
            for (int i = 0; i < names.size(); i++) {
                int rand = Randoms.pickNumberInRange(0, 9);
                if (rand >= 4) {
                    positions.set(i, positions.get(i) + 1);
                }
                System.out.println(names.get(i) + " : " + dash(positions.get(i)));
            }
            System.out.println();
        }

        int max = 0;
        for (int p : positions) {
            if (p > max) {
                max = p;
            }
        }

        StringJoiner winners = new StringJoiner(", ");
        for (int i = 0; i < names.size(); i++) {
            if (positions.get(i) == max) {
                winners.add(names.get(i));
            }
        }

        System.out.println("최종 우승자 : " + winners.toString());
    }

    private static List<String> parseNames(String line) {
        if (line == null) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }
        String[] parts = line.split(",");
        List<String> names = new ArrayList<>();
        for (String raw : parts) {
            String name = raw.trim();
            if (name.isEmpty() || name.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 비어있을 수 없고 5자 이하만 허용됩니다.");
            }
            names.add(name);
        }
        if (names.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }
        return names;
    }

    private static int parseAttempts(String line) {
        if (line == null) {
            throw new IllegalArgumentException("시도 횟수를 입력해야 합니다.");
        }
        int attempts;
        try {
            attempts = Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }
        if (attempts <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상의 정수여야 합니다.");
        }
        return attempts;
    }

    private static String dash(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("-");
        }
        return sb.toString();

    }
}
