package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.SemanticPass.Function;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	List<Function> functions;
	//boolean desigIsArray = false; 
	boolean AddOn = false;
	boolean newArray = false;
	boolean valOnStack = false;
	int forEachTop = 0;
	int forEachNoMore = 0;
//	int whileEndAdr = 0;
	
	Stack<Integer> whileStartStack = new Stack<Integer>();
	Stack<Integer> whileFixUpStack = new Stack<Integer>();
	Stack<Integer> ifFixUp = new Stack<Integer>();
	Stack<Integer> elseFixUp = new Stack<Integer>();
	
	public CodeGenerator(List<Function> fs) {
		functions = fs;
	}
	
	public int getParamCount(String methodName) {
		int count = 0;
    	if (!functions.isEmpty()) {
    	for (Function function : functions) {
    		if (function.getFunctionName().equals(methodName))
             count = function.getArguments().size();
        }	   
    }
	return count;	
	}
	
	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	
	
	public void visit(StatementPrint sPrint) {
		if(sPrint.getExpr().struct.getKind() == 3) {
			if(sPrint.getExpr().struct.getElemType().getKind()==1 || sPrint.getExpr().struct.getKind() == 5) {
				Code.loadConst(5);
				Code.put(Code.print);
			}
			else
			{
				Code.loadConst(1);
				Code.put(Code.bprint);
			}			
		}
		else if(sPrint.getExpr().struct.getKind() == 1 || sPrint.getExpr().struct.getKind() == 5)
		{
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
		
	}
	
	public void visit(StatementPrintWithConst sPrint) {
		int offset = sPrint.getN2();
		
		Code.loadConst(offset);
		
		if(sPrint.getExpr().struct.getKind() == 3) {
			if(sPrint.getExpr().struct.getElemType().getKind()==1 || sPrint.getExpr().struct.getKind() == 5) {
				Code.put(Code.print);
			}
			else
			{
				Code.put(Code.bprint);
			}			
		}
		else if(sPrint.getExpr().struct.getKind() == 1 || sPrint.getExpr().struct.getKind() == 5)
		{
			Code.put(Code.print);
		}
		else{
			Code.put(Code.bprint);
		}
	}
	
	public void visit(FactorNumber fNumber) {
		Obj con = Tab.insert(Obj.Con,"$" , fNumber.struct);
		con.setLevel(0);
		con.setAdr(fNumber.getN());
		
		Code.load(con);
	}
	
	public void visit(FactorChar fChar) {
		Obj con = Tab.insert(Obj.Con,"$" , fChar.struct);
		con.setLevel(0);
		con.setAdr(fChar.getC());
		
		Code.load(con);
	}
	
	public void visit(FactorBool fBool) {
		int b = 0;
		Obj con = Tab.insert(Obj.Con,"$" , fBool.struct);
		con.setLevel(0);
		
		if(fBool.getB())
			b = 1;
		
		con.setAdr(b);
		
		Code.load(con);
	}
	
	
	public void visit(MethodTypeNameType methodTypeNameType) {
		
		if("main".equals(methodTypeNameType.getMethodName())) {
			mainPc = Code.pc;
			report_info("Usao", methodTypeNameType);
		}
		
		methodTypeNameType.obj.setAdr(Code.pc);
		
		report_info(String.valueOf(getParamCount(methodTypeNameType.getMethodName())),null);
		report_info(String.valueOf(methodTypeNameType.obj.getLocalSymbols().size()),null);
		
		Code.put(Code.enter);
		Code.put(getParamCount(methodTypeNameType.getMethodName()));
		Code.put(methodTypeNameType.obj.getLocalSymbols().size());
	}
	
	public void visit(MethodTypeNameVoid methodTypeNameVoid) {
		if("main".equals(methodTypeNameVoid.getMethodName())) {
			mainPc = Code.pc;
		}
		
		methodTypeNameVoid.obj.setAdr(Code.pc);
				
		Code.put(Code.enter);
		Code.put(getParamCount(methodTypeNameVoid.getMethodName()));
		Code.put(methodTypeNameVoid.obj.getLocalSymbols().size());
	}
	
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignatorStatementIs dsIS) {
		
//		report_info("RRRRRRRRRRRRRRR",null);
		//Obj obj = Tab.find(dsIS.getDesignator().getName());
//		report_info(dsIS.getDesignator().getName(),null);
//		report_info(String.valueOf( obj.getType().getKind()) ,null);
//		report_info("RRRRRRRRRRRRRRRR",null);
		
		
		Obj obj = dsIS.getDesignator().obj;
		
		if(obj.getType().getKind() == 3 && !newArray) {					
			Code.load(obj);								
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			if(obj.getType().getElemType().getKind() == 1 || obj.getType().getElemType().getKind() == 5) {
				Code.put(Code.astore);
			}
			else {
				Code.put(Code.bastore);
			}
		}		
		else {
			Code.store(obj);
			newArray = false;
		}
	}
	
	public void visit(Designator designator) {

		SyntaxNode parent = designator.getParent();
		
		if(parent instanceof FactorDesignator) {
			Obj obj = ((FactorDesignator) parent).getDesignator().obj;
			if(obj.getType().getKind() == 3 && AddOn) {
				report_info("USAO2",null);
				Code.load(obj);
				Code.put(Code.dup_x1);
				Code.put(Code.pop);
				if(obj.getType().getElemType().getKind() == 1 || obj.getType().getElemType().getKind() == 5) {
					Code.put(Code.aload);
				}
				else {
					Code.put(Code.baload);
				}
				AddOn = false;
			}
			else {
				Code.load(obj);				
			}
		
		}
		
	}
	
	public void visit(DesignatorAddOn designAddOn) {
		AddOn = true;
	}
	
	public void visit(DesignatorStatementIncr desINCR) {

		Obj obj = desINCR.getDesignator().obj;
		
		if(obj.getType().getKind() == 3) {

			Code.put(Code.dup);
			Code.load(obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.aload);
			
			Code.loadConst(1);
			Code.put(Code.add);
			
			Code.load(obj);
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			Code.put(Code.astore);
		}
		else {
			Code.load(desINCR.obj);
			Code.loadConst(1);
			Code.put(Code.add);
			
			Code.store(desINCR.getDesignator().obj);
		}
		
		
	}
	
	public void visit(DesignatorStatementDecr desDECR) {

		Obj obj = desDECR.getDesignator().obj;
		
		if(obj.getType().getKind() == 3) {

			Code.put(Code.dup);
			Code.load(obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.aload);
			
			Code.loadConst(-1);
			Code.put(Code.add);
			
			Code.load(obj);
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			Code.put(Code.astore);
		}
		else {
			Code.load(desDECR.obj);
			Code.loadConst(-1);
			Code.put(Code.add);
			
			Code.store(desDECR.getDesignator().obj);
		}
		
	}
	
	public void visit(ExprMany exprMany) {
		if(exprMany.getAddop() instanceof AddopP) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
	
	public void visit(TermMany termMany) {
		if(termMany.getMulop() instanceof MulopM) {
			Code.put(Code.mul);
		}
		else if(termMany.getMulop() instanceof MulopD){
			Code.put(Code.div);
		}
		else {
			Code.put(Code.rem);
		}
	}
	
	public void visit(ExprOneNeg exprOneNeg) {
		Code.put(Code.neg);
	}
	
	public void visit(FactorDesActPar fdActPar) {
		Obj functionObj = fdActPar.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		
		
		report_info(fdActPar.getDesignator().getName(),null);
		
		if(fdActPar.getDesignator().getName().equals("len")) {
			Code.put(Code.arraylength);
			return;
		}	
		
		if(fdActPar.getDesignator().getName().equals("chr")) {
			return;
		}	
		
		if(fdActPar.getDesignator().getName().equals("ord")) {
			return;
		}	
		
		Code.put(Code.call);
		Code.put2(offset);
		
		if(fdActPar.getOptActPars().getActPars() instanceof ActParsNone) {
			
		}
	}
	
	public void visit(DesignatorStatementAct dsAct) {
		Obj functionObj = dsAct.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		
		report_info("asdasd",null);
		
		if(dsAct.getDesignator().getName().equals("len")) {
			Code.put(Code.arraylength);
			return;
		}
		
		if(dsAct.getDesignator().getName().equals("chr")) {
			return;
		}	
		
		if(dsAct.getDesignator().getName().equals("ord")) {
			return;
		}	
		
		Code.put(Code.call);
		Code.put2(offset);

		if(dsAct.getDesignator().obj.getType().getKind() != 0) {
			Code.put(Code.pop);
		}
	}
	
	public void visit(StatementReturnExpr sRE) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(StatementReturnNoExpr sRNE) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(FactorNewExpr factorNewExpr) {
		newArray = true;
		int b = 0;
		if(factorNewExpr.getType().struct.getKind() == 1)
			b = 1;
//		report_info(String.valueOf(b) ,null);
//		report_info("WWWWWWWWWWWWWWW",null);
			
		Code.put(Code.newarray);
		Code.put(b);
	}
	
	public void visit(StatementRead statementRead) {
		if(statementRead.getDesignator().obj.getType().getKind() == 3) {
			if(statementRead.getDesignator().obj.getType().getElemType().getKind() == 1 ) {
				Code.put(Code.read);
				Code.load(statementRead.getDesignator().obj);
				Code.put(Code.dup_x2);
				Code.put(Code.pop);
				Code.put(Code.astore);
				return;
			}
			else
			{
				Code.put(Code.bread);
				Code.load(statementRead.getDesignator().obj);
				Code.put(Code.dup_x2);
				Code.put(Code.pop);
				Code.put(Code.bastore);
				return;
			}			
		}
		else if(statementRead.getDesignator().obj.getType().getKind() == 1)
		{
			Code.put(Code.read);
		}
		else{
			Code.put(Code.bread);
		}
		Code.store(statementRead.getDesignator().obj);
	}
	
	public void swap() {
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
	}
	
	public void visit(DesignatorStatementCount dsCount) {
		boolean isInt = false;
		Obj objReturn = dsCount.getDesignator().obj;
		Obj objArray = dsCount.getDesignator1().obj;		
		if(objArray.getType().getElemType().getKind() == 1)
			isInt = true;
		
		Code.loadConst(0);
		Code.loadConst(0);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		// 2 0 0 
		
		
		// Pocetak petlje cnt 2 0 / cnt 2 1 / cnt 2 2 / 2 3
		int top = Code.pc;
		Code.put(Code.dup_x1);
		// 0 2 0
		
		
		Code.load(objArray);
		swap();
		Code.load(objArray);
		Code.put(Code.arraylength);
		Code.put(Code.dup2);
		Code.putFalseJump( Code.ne , 0);
		int noMore = Code.pc - 2;
		Code.put(Code.pop);
		
		if(isInt)
		Code.put(Code.aload);
		else
		Code.put(Code.baload);
			
		Code.put(Code.dup2);
		Code.putFalseJump( Code.ne , 0);
		int foundCheckAddr = Code.pc - 2;
		// 0 3 2 7
		Code.put(Code.pop);
		// 0 3 2
		swap();
		// 0 2 3
		Code.loadConst(1);
		Code.put(Code.add);
		// 0 2 4
		Code.putJump(top);
		
		Code.fixup(foundCheckAddr);
		//4 stvari na steku count iter Expr currVal 0 3 2 7
		Code.put(Code.pop);
		// 0 3 2
		
		Code.put(Code.dup_x2);
		// 2 0 3 2
		Code.put(Code.pop);
		// 2 0 3
		Code.put(Code.dup_x1);
		// 2 3 0 3
		Code.put(Code.pop);
		
		// 2 3 0
		Code.loadConst(1);
		Code.put(Code.add);
		// 2 3 1
		
		Code.put(Code.dup_x2);
		// 1 2 3 1
		Code.put(Code.pop);
		// 1 2 3
		Code.loadConst(1);
		Code.put(Code.add);
		// 1 2 4
		Code.putJump(top);
				
		Code.fixup(noMore);
		//5 stvari na steku
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		


		Code.store(objReturn);
		
		
	}
	
	public void visit(DesignatorStatementBin dsBin){
		Obj objReturn = dsBin.getDesignator().obj;
		Obj objArray = dsBin.getDesignator1().obj;
		
		
		//empty stack
		Code.loadConst(0);
		Code.loadConst(1);
		
		Code.load(objArray);
		Code.put(Code.arraylength);
		Code.loadConst(1);
		Code.put(Code.sub);
		
		//  sum mult n-1 
		
		int top = Code.pc;
		Code.put(Code.dup_x2);
		// n-1 sum mult n-1 
		Code.load(objArray);
		// n-1 sum mult n-1 adr
		Code.put(Code.dup_x1);
		// n-1 sum mult adr n-1 adr
		Code.put(Code.pop);
		// n-1 sum mult adr n-1	
		Code.loadConst(1);
		Code.put(Code.add);
		// DODAJE SE 1 ZBOG PROVERE
		Code.loadConst(0);
		// n-1 sum mult adr n-1 0	
		Code.put(Code.dup2);
		// n-1 sum mult adr n-1 0 n-1 0
		Code.putFalseJump( Code.ne , 0);
		int noMore = Code.pc - 2;
		// n-1 sum mult adr n-1 0	
		Code.put(Code.pop);
		// n-1 sum mult adr n-1
		Code.loadConst(1);
		Code.put(Code.sub);
		//SKIDA SE 1 ZBOG PPROVERE
		Code.put(Code.aload);
		// n-1 sum mult elem
		Code.put(Code.dup2);
		// n-1 sum mult elem mult elem
		Code.put(Code.mul);
		//n-1 sum mult elem multElem
		Code.put(Code.dup_x2);
		//n-1 sum multElem mult elem multElem
		Code.put(Code.pop);
		Code.put(Code.pop);
		//n-1 sum multElem mult
		Code.loadConst(2);
		Code.put(Code.mul);
		// n-1 sum multElem newMult
		Code.put(Code.dup_x2);
		// n-1 newMult sum multElem newMult
		Code.put(Code.pop);
		// n-1 newMult sum multElem
		Code.put(Code.add);
		// n-1 newMult newSum
		Code.put(Code.dup_x2);
		// newSum n-1 newMult newSum
		Code.put(Code.pop);
		// newSum n-1 newMult
		Code.put(Code.dup_x1);
		//  newSum newMult n-1 newMult
		Code.put(Code.pop);
		//  newSum newMult n-1
		Code.loadConst(1);
		Code.put(Code.sub);
		// newSum newMult newN
		Code.putJump(top);
		
		
		
		
		Code.fixup(noMore);
		// n-1 sum mult adr n-1 0
		Code.put(Code.pop);	
		Code.put(Code.pop);	
		Code.put(Code.pop);	
		Code.put(Code.pop);	
		swap();
		Code.put(Code.pop);	
		
		Code.store(objReturn);
		
	}
	
	public void visit(DesignatorStatementMax dsMax) {
		boolean isInt = false;
		Obj objReturn = dsMax.getDesignator().obj;
		Obj objArray = dsMax.getDesignator1().obj;		
		if(objArray.getType().getElemType().getKind() == 1)
			isInt = true;
		
		Code.loadConst(0);
		Code.loadConst(0);
		// 0 0 
		
		
		// Pocetak petlje max cnt
		int top = Code.pc;
		Code.put(Code.dup_x1);
		
		Code.load(objArray);
		swap();
		Code.load(objArray);
		Code.put(Code.arraylength);
		Code.put(Code.dup2);
		Code.putFalseJump( Code.ne , 0);
		int noMore = Code.pc - 2;
		Code.put(Code.pop);
		
		if(isInt)
		Code.put(Code.aload);
		else
		Code.put(Code.baload);
			
		Code.put(Code.dup2);
		Code.putFalseJump( Code.ge , 0);
		int foundCheckAddr = Code.pc - 2;
		// 0 7 3 - max > elem
		Code.put(Code.pop);
		// 0 7
		swap();
		// 7 0
		Code.loadConst(1);
		Code.put(Code.add);
		// 7 1
		Code.putJump(top);
		
		Code.fixup(foundCheckAddr);
		// 0 3 8
		Code.put(Code.dup_x1);
		// 0 8 3 8
		Code.put(Code.pop);
		// 0 8 3
		Code.put(Code.pop);
		// 0 8
		swap();
		// 8 0
		Code.loadConst(1);
		Code.put(Code.add);
		// 8 1
		Code.putJump(top);
				
		Code.fixup(noMore);
		//5 stvari na steku
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		swap();
		Code.put(Code.pop);
		


		Code.store(objReturn);
		
		
	}
	
	
	public void visit(DesignatorStatementFindAny dsFindAny) {
		boolean isInt = false;
		Obj objReturn = dsFindAny.getDesignator().obj;
		Obj objArray = dsFindAny.getDesignator1().obj;		
		if(objArray.getType().getElemType().getKind() == 1)
			isInt = true;
		
		// 2
		
		Code.loadConst(0);
		// 2 0
		
		
		// Pocetak petlje 2 0 / 2 1 / 2 2 / 2 3
		int top = Code.pc;
		Code.put(Code.dup_x1);
		// 0 2 0
		
		
		Code.load(objArray);
		swap();
		Code.load(objArray);
		Code.put(Code.arraylength);
		Code.put(Code.dup2);
		Code.putFalseJump( Code.ne , 0);
		int noMore = Code.pc - 2;
		Code.put(Code.pop);
		
		if(isInt)
		Code.put(Code.aload);
		else
		Code.put(Code.baload);
			
		Code.put(Code.dup2);
		Code.putFalseJump( Code.ne , 0);
		int foundCheckAddr = Code.pc - 2;
		Code.put(Code.pop);
		swap();
		Code.loadConst(1);
		Code.put(Code.add);
		Code.putJump(top);
		
		Code.fixup(foundCheckAddr);
		//3 stvari na steku
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.loadConst(1);		
		Code.putJump(0);
		int endTrue = Code.pc - 2;
				
		Code.fixup(noMore);
		//5 stvari na steku
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.loadConst(0);

		
		Code.fixup(endTrue);

		Code.store(objReturn);
		
	}
	
	public void visit(DesignatorStatementFindAndReplace dsFindAndReplace) {
		boolean isInt = false;
		Obj objReturnArray = dsFindAndReplace.getDesignator().obj;
		Obj objArrayLoop = dsFindAndReplace.getDesignator1().obj;	
		Obj ident = dsFindAndReplace.getDesignator2().obj;
		
		if(objArrayLoop.getType().getElemType().getKind() == 1)
			isInt = true;
		
		Code.load(objArrayLoop);
		Code.put(Code.arraylength);		
		Code.put(Code.newarray);
		Code.put(1);		
		Code.store(objReturnArray);
		
		Code.store(ident);
		
		
		Code.loadConst(0);
		// 2 0
		
		
		// Pocetak petlje 2 0 / 2 1 / 2 2 / 2 3
		int top = Code.pc;
		Code.put(Code.dup_x1);
		// 0 2 0
		
		
		Code.load(objArrayLoop);
		swap();
		Code.load(objArrayLoop);
		Code.put(Code.arraylength);
		Code.put(Code.dup2);
		Code.putFalseJump( Code.ne , 0);
		int noMore = Code.pc - 2;
		Code.put(Code.pop);
		
		if(isInt)
		Code.put(Code.aload);
		else
		Code.put(Code.baload);
		
		Code.put(Code.dup2);
		Code.putFalseJump( Code.eq , 0);
		int notEqual = Code.pc - 2;
	
		Code.put(Code.pop);
		Code.load(ident);
		Code.put(Code.dup);
		Code.store(ident);
		
		// razlicito
		
		Code.fixup(notEqual);
		swap();
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup2);
		Code.load(objReturnArray);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		
		if(isInt)
		Code.put(Code.astore);
		else
		Code.put(Code.bastore);
		
		Code.put(Code.pop);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.putJump(top);
		
				
		Code.fixup(noMore);
		//5 stvari na steku
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		
	
		
	}
	
	public void visit(ForEachBegin forEachBegin) {
		boolean isInt = false;
		Obj objArray = forEachBegin.getDesignator().obj;
		
		if(objArray.getType().getElemType().getKind() == 1)
			isInt = true;
		
		Code.load(objArray);
		Code.put(Code.arraylength);
		Code.loadConst(0);
		forEachTop = Code.pc;
		Code.put(Code.dup2);
		Code.putFalseJump( Code.ne , 0);
		forEachNoMore = Code.pc - 2;
		
		Code.put(Code.dup);
		Code.load(objArray);
		swap();
		
		if(isInt)
		Code.put(Code.aload);
		else
		Code.put(Code.baload);
		
		Code.store(forEachBegin.getDesignator1().obj);
		//Code.put(Code.dup_x1);
		
	}
	
	public void visit(ForEachEnd forEachEnd) {
		Code.loadConst(1);
		Code.put(Code.add);
		Code.putJump(forEachTop);
		
		Code.fixup(forEachNoMore);
	}
//	public void visit(StatementForEach forEach) {
//		Code.store(forEach.getDesignator().obj);
//	}
	
	
	public void visit(IfBegin ifBegin) {
		
	}
	
	public void visit(IfCondition ifCondition) {
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		if(ifCondition.getParent() instanceof StatementIf) {
			ifFixUp.push(Code.pc - 2);
		}
		else if(ifCondition.getParent() instanceof StatementElse){
			elseFixUp.push(Code.pc - 2);
		}
	}
	
	
	public void visit(ElseBegin elseBegin) {
		//pocetak else i kraj if-a
		
		Code.putJump(0);
		ifFixUp.push(Code.pc - 2);
		
		Code.fixup(elseFixUp.pop());	
	}	
	
	public void visit(IfEnd ifEnd) {
		Code.fixup(ifFixUp.pop());
	}
	
	public void visit(IfElseEnd ifElseEnd) {
		Code.fixup(ifFixUp.pop());
	}
	
	
	public void visit(WhileBegin whileBegin) {
		
//		Code.loadConst(0);
//		Code.putFalseJump( Code.ne , 0);
//		whileEndAdr = Code.pc - 2;
//		
		
		whileStartStack.push(Code.pc);
		
		
	}
	
	public void visit(WhileCondition whileCondition) {
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		whileFixUpStack.push(Code.pc - 2);
	}
	
	public void visit(WhileEnd whileEnd) {
		//Code.fixup(whileEndAdr);
		
		Code.putJump(whileStartStack.pop());		
		Code.fixup(whileFixUpStack.pop());
		
	}
	
	public void visit(StatementBreak statementBreak) {
		Code.putJump(0);
		whileFixUpStack.push(Code.pc - 2);
	}
	
	public void visit(StatementContinue statementContinue) {
		Code.putJump(whileStartStack.peek());
	}
	
	//public void visit()
	
	public void visit(CondTermMany condTermMany) {
		//AND
		Code.put(Code.mul);
	}
	
	public void visit(ConditionMany condMany) {
		//OR
		int condTrue = 0;
		int skip = 0;
		Code.put(Code.add);
		Code.loadConst(1);
		Code.putFalseJump( Code.lt , 0);
		condTrue = Code.pc - 2;
		Code.loadConst(0);
		Code.putJump(0);
		skip = Code.pc - 2;
				
		Code.fixup(condTrue);
		Code.loadConst(1);
		
		Code.fixup(skip);
	}
	
	public void visit(CondFactTwo condFactTwo) {
		int condTrue = 0;
		int skip = 0;
		if(condFactTwo.getRelop() instanceof RelopE) {
			Code.putFalseJump( Code.ne , 0);
			condTrue = Code.pc - 2;
			Code.loadConst(0);
		}
		else if(condFactTwo.getRelop() instanceof RelopNE) {
			Code.putFalseJump( Code.eq , 0);
			condTrue = Code.pc - 2;
			Code.loadConst(0);
		}
		else if(condFactTwo.getRelop() instanceof RelopGRT) {
			Code.putFalseJump( Code.le , 0);
			condTrue = Code.pc - 2;
			Code.loadConst(0);
		}
		else if(condFactTwo.getRelop() instanceof RelopGRTE) {
			Code.putFalseJump( Code.lt , 0);
			condTrue = Code.pc - 2;
			Code.loadConst(0);
		}
		else if(condFactTwo.getRelop() instanceof RelopLT) {
			Code.putFalseJump( Code.ge , 0);
			condTrue = Code.pc - 2;
			Code.loadConst(0);
		}
		else if(condFactTwo.getRelop() instanceof RelopLTE) {
			Code.putFalseJump( Code.gt , 0);
			condTrue = Code.pc - 2;
			Code.loadConst(0);
		}
		Code.putJump(0);
		skip = Code.pc - 2;
		
		Code.fixup(condTrue);
		Code.loadConst(1);
	
		Code.fixup(skip);
	}
}
