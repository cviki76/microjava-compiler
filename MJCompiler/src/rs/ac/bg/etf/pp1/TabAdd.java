package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class TabAdd extends Tab{

	
	public static void initAdd() {
		Tab.init();
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", new Struct(5)));
	}
}
