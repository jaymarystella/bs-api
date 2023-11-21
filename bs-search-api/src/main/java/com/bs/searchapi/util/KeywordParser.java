package com.bs.searchapi.util;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

import java.util.List;
import java.util.stream.Collectors;

public class KeywordParser {
    private static final Komoran KEYWORD_PARSER = new Komoran(DEFAULT_MODEL.FULL);
    private static final String NOUN_PREFIX = "NN";

    /**
     * 참고문서: https://docs.komoran.kr/firststep/postypes.html
     * NN: 일반명사
     * NNG: 일반명사
     * NNP: 고유명사
     * NNB: 의존명사
     */
    public static List<String> parseKeywords(String input) {
        KomoranResult analyze = KEYWORD_PARSER.analyze(input);
        List<Token> tokenList = analyze.getTokenList();
        return tokenList.stream()
                .filter(token -> token.getPos().startsWith(NOUN_PREFIX))
                .map(Token::getMorph)
                .collect(Collectors.toList());
    }
}
