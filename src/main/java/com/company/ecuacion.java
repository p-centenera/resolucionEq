package com.company;
import java.util.stream.Stream;
import java.util.function.DoubleUnaryOperator;
class ecuacion{
public static double raiz(DoubleUnaryOperator F, double a, double b, double p){
	intervalo i=  Stream
		.iterate(new intervalo (a,b), n -> (F.applyAsDouble((n.a+n.b)/2.0)*F.applyAsDouble(n.a)<0)? new intervalo(n.a, (n.a+n.b)/2): new intervalo((n.a+n.b)/2,n.b))
		.filter(n->Math.abs(n.b-n.a)<p) 
                .findFirst()
		.orElse(null);
	if (i!= null)
		return i.a;
	else
		return a-1;
}
}
