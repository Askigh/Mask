// Generated from C:/Users/antoi/Desktop/Programmation Java/WorkSpace/Atosym/src/main/antlr\Atosym.g4 by ANTLR 4.7.2
package net.akami.atosym.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AtosymParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, FUNC=5, NUMBER=6, DIGIT=7, CHAR=8, SUM=9, 
		SUB=10, MULT=11, DIV=12, POW=13, WHITESPACE=14;
	public static final int
		RULE_main = 0, RULE_exp = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"main", "exp"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'!'", null, null, null, null, "'+'", "'-'", 
			"'*'", "'/'", "'^'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "FUNC", "NUMBER", "DIGIT", "CHAR", "SUM", 
			"SUB", "MULT", "DIV", "POW", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Atosym.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AtosymParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MainContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AtosymListener ) ((AtosymListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AtosymListener ) ((AtosymListener)listener).exitMain(this);
		}
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_main);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			exp(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Token unop;
		public Token binop;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode FUNC() { return getToken(AtosymParser.FUNC, 0); }
		public TerminalNode NUMBER() { return getToken(AtosymParser.NUMBER, 0); }
		public TerminalNode CHAR() { return getToken(AtosymParser.CHAR, 0); }
		public TerminalNode SUM() { return getToken(AtosymParser.SUM, 0); }
		public TerminalNode SUB() { return getToken(AtosymParser.SUB, 0); }
		public TerminalNode POW() { return getToken(AtosymParser.POW, 0); }
		public TerminalNode DIV() { return getToken(AtosymParser.DIV, 0); }
		public TerminalNode MULT() { return getToken(AtosymParser.MULT, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AtosymListener ) ((AtosymListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AtosymListener ) ((AtosymListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case FUNC:
				{
				setState(8);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FUNC) {
					{
					setState(7);
					match(FUNC);
					}
				}

				setState(10);
				match(T__0);
				setState(16);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(11);
						exp(0);
						setState(12);
						match(T__1);
						}
						} 
					}
					setState(18);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(19);
				exp(0);
				setState(20);
				match(T__2);
				}
				break;
			case NUMBER:
				{
				setState(22);
				match(NUMBER);
				}
				break;
			case CHAR:
				{
				setState(23);
				match(CHAR);
				}
				break;
			case SUM:
			case SUB:
				{
				setState(24);
				((ExpContext)_localctx).unop = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SUM || _la==SUB) ) {
					((ExpContext)_localctx).unop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(25);
				exp(5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(60);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(58);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(28);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(29);
						((ExpContext)_localctx).binop = match(POW);
						setState(30);
						exp(5);
						}
						break;
					case 2:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(31);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(32);
						((ExpContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
							((ExpContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						setState(33);
						exp(3);
						}
						break;
					case 3:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(34);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(35);
						((ExpContext)_localctx).binop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SUM || _la==SUB) ) {
							((ExpContext)_localctx).binop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(36);
						exp(2);
						}
						break;
					case 4:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(37);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(38);
						((ExpContext)_localctx).unop = match(T__3);
						}
						break;
					case 5:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(39);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(56);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__0:
						case FUNC:
							{
							setState(41);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==FUNC) {
								{
								setState(40);
								match(FUNC);
								}
							}

							setState(43);
							match(T__0);
							setState(49);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
							while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
								if ( _alt==1 ) {
									{
									{
									setState(44);
									exp(0);
									setState(45);
									match(T__1);
									}
									} 
								}
								setState(51);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
							}
							setState(52);
							exp(0);
							setState(53);
							match(T__2);
							}
							break;
						case CHAR:
							{
							setState(55);
							match(CHAR);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					}
					} 
				}
				setState(62);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\20B\4\2\t\2\4\3\t"+
		"\3\3\2\3\2\3\3\3\3\5\3\13\n\3\3\3\3\3\3\3\3\3\7\3\21\n\3\f\3\16\3\24\13"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\35\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3,\n\3\3\3\3\3\3\3\3\3\7\3\62\n\3\f\3\16\3"+
		"\65\13\3\3\3\3\3\3\3\3\3\5\3;\n\3\7\3=\n\3\f\3\16\3@\13\3\3\3\2\3\4\4"+
		"\2\4\2\4\3\2\13\f\3\2\r\16\2L\2\6\3\2\2\2\4\34\3\2\2\2\6\7\5\4\3\2\7\3"+
		"\3\2\2\2\b\n\b\3\1\2\t\13\7\7\2\2\n\t\3\2\2\2\n\13\3\2\2\2\13\f\3\2\2"+
		"\2\f\22\7\3\2\2\r\16\5\4\3\2\16\17\7\4\2\2\17\21\3\2\2\2\20\r\3\2\2\2"+
		"\21\24\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\25\3\2\2\2\24\22\3\2\2\2"+
		"\25\26\5\4\3\2\26\27\7\5\2\2\27\35\3\2\2\2\30\35\7\b\2\2\31\35\7\n\2\2"+
		"\32\33\t\2\2\2\33\35\5\4\3\7\34\b\3\2\2\2\34\30\3\2\2\2\34\31\3\2\2\2"+
		"\34\32\3\2\2\2\35>\3\2\2\2\36\37\f\6\2\2\37 \7\17\2\2 =\5\4\3\7!\"\f\4"+
		"\2\2\"#\t\3\2\2#=\5\4\3\5$%\f\3\2\2%&\t\2\2\2&=\5\4\3\4\'(\f\b\2\2(=\7"+
		"\6\2\2):\f\5\2\2*,\7\7\2\2+*\3\2\2\2+,\3\2\2\2,-\3\2\2\2-\63\7\3\2\2."+
		"/\5\4\3\2/\60\7\4\2\2\60\62\3\2\2\2\61.\3\2\2\2\62\65\3\2\2\2\63\61\3"+
		"\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\5\4\3\2\678\7\5"+
		"\2\28;\3\2\2\29;\7\n\2\2:+\3\2\2\2:9\3\2\2\2;=\3\2\2\2<\36\3\2\2\2<!\3"+
		"\2\2\2<$\3\2\2\2<\'\3\2\2\2<)\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?\5"+
		"\3\2\2\2@>\3\2\2\2\n\n\22\34+\63:<>";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}