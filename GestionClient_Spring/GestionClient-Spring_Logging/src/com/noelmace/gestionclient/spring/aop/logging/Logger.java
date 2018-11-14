package com.noelmace.gestionclient.spring.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

/**
 * Classe de logging via AOP grâce à AspectJ
 * pré et post-traitement sur les méthodes interceptées
 * 
 * la définition du pointcut n'est pas réalisée dans ce composant, il reste donc à le définir (via Spring par exemple)
 *
 * @author Noël Macé (noelmace.com)
 * @see http://ewawszczyk.developpez.com/tutoriel/java/spring/aop/
 */
public class Logger {

	/**
	 * Méthode de pré-traitement (exécutée avant la méthode interceptée)
	 * @param joinPoint 
	 */
	public void logMethodEntry(JoinPoint joinPoint) {

		Object[] args = joinPoint.getArgs();

		// Nom de la méthode interceptée
		String name = joinPoint.getSignature().toLongString();
		StringBuffer sb = new StringBuffer(name + " called with: [");

		// Liste des valeurs des arguments reçus par la méthode
		for(int i = 0; i < args.length; i++) {
			Object o = args[i];
			sb.append("'"+o+"'");
			sb.append((i == args.length - 1) ? "" : ", ");
		}
		sb.append("]");

		System.out.println(sb);
	}

	
	/**
	 * Méthode de post-traitement (exécutée après la méthode interceptée)
	 * @param staticPart
	 * @param result retour de la méthode interceptée
	 */
	public void logMethodExit(StaticPart staticPart, Object result) {

		// Nom de la méthode interceptée
		String name = staticPart.getSignature().toLongString();

		System.out.println(name + " returning: [" + result + "]");
	}
}
