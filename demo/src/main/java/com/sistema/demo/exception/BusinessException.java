package com.sistema.demo.exception;

import java.io.Serializable;
import java.lang.reflect.Method;

public class BusinessException extends Exception implements Serializable {

	private static final long serialVersionUID = -5565612478958589932L;

	private String message;

	/**
	 * MantÃ©m os parÃ¢metros da messagem do erro
	 */
	private String[] paramsOfMessage;

	/**
	 * MantÃ©m os parÃ¢metros do mÃ©todo que gerou a exceÃ§Ã£o.
	 */
	private Object[] paramsOfMethod;

	/**
	 * Classe a qual pertence o mÃ©todo que gerou a exceÃ§Ã£o.
	 */
	private Class<?> classe;

	/**
	 * Construtor default da Classe IntranetException
	 */
	public BusinessException() {
		paramsOfMethod = null;
		classe = null;
	}

	/**
	 * Contrutor simples da Classe IntranetException
	 *
	 * @param e - Exception.
	 */
	public BusinessException(final Exception e) {
		super(e);
	}

	/**
	 * @param e
	 */
	public BusinessException(final String cause) {
		super(new Exception(cause + "\n"));
		message = cause;
	}

	/**
	 * @param cause
	 * @param paramsOfMessage
	 */
	public BusinessException(final String cause, final String[] paramsOfMessage) {
		this(cause);
		this.paramsOfMessage = paramsOfMessage;
	}

	/**
	 * Construtor parcial da Classe IntranetException
	 *
	 * @param e              - Exception
	 * @param paramsOfMethod - ParÃ¢metros que foram passados no mÃ©todo que lanÃ§ou
	 *                       a exceÃ§Ã£o.
	 */
	public BusinessException(final Exception e, final Object[] paramsOfMethod) {
		super(e);
		this.paramsOfMethod = paramsOfMethod;
	}

	/**
	 * Construtor completo da ClasseIntranetException
	 *
	 * @param e              - Exception.
	 * @param paramsOfMethod - ParÃ¢metros que foram passados no mÃ©todo que lanÃ§ou
	 *                       a exceÃ§Ã£o.
	 * @param classe         - Classe a qual pertence o mÃ©todo que lanÃ§ou a
	 *                       exceÃ§Ã£o.
	 */
	public BusinessException(final Exception e, final Object[] paramsOfMethod, final Class<?> classe) {
		super(e);
		this.paramsOfMethod = paramsOfMethod;
		this.classe = classe;
	}

	/**
	 * Retorna apenas o nome do mÃ©todo.
	 *
	 * @return String
	 */
	public String getMethodName() {
		return getStackTrace()[0].getMethodName();
	}

	/**
	 * Retorna o nome da classe que pertence o mÃ©todo que lanÃ§ou a exceÃ§Ã£o.
	 *
	 * @return String
	 */
	public String getMethodClass() {
		return getStackTrace()[0].getClassName();
	}

	/**
	 * Retorna a linha a qual iniciou a causa da Exception.
	 *
	 * @return int
	 */
	public int getLineInitException() {
		return getStackTrace()[0].getLineNumber();
	}

	/**
	 * Metodo que retorna o estado, dos itens passados por parÃ¢metro no mÃ©todo que
	 * lanÃ§ou a exceÃ§Ã£o. Esse mÃ©todo Ã© indicado para o envio de informaÃ§Ãµes
	 * de exceÃ§Ã£o pertinente ao estado dos parÃ¢metros passados em um mÃ©todo que
	 * lanÃ§ou a exceÃ§Ã£o, via e-mail.
	 *
	 * @return String
	 */
	public String getValuesAllParametersOfMethod() {
		String result = "";
		Class<?> klass = null;
		Method method = null;
		Class<?>[] parameterTypes = null;
		Method[] methods = new Method[0];

		if (paramsOfMethod != null) {
			for (int i = 0; i < paramsOfMethod.length; i++) {
				if (paramsOfMethod[i] != null) {
					klass = paramsOfMethod[i].getClass();

					if (klass != null) {
						methods = klass.getMethods();
					}

					final String typeKlass = klass == null ? "null" : klass.toString();

					result += "<tr bgcolor=\"#0033CC\">" + "<td width=\"38%\"><font color=\"#FFFFFF\"> Paramentro: " + i
							+ "</font></td>" + "<td width=\"32%\"><font color=\"#FFFFFF\"> Tipo: " + typeKlass
							+ "</font></td>" + "<td width=\"30%\"><font color=\"#FFFFFF\"> Valor Geral: "
							+ paramsOfMethod[i] + "</font></td>" + "</tr>" + "<tr bgcolor=\"#999999\">"
							+ "<td colspan=\"3\">" + "<div align=\"center\">Detalhes do Objeto</div>" + "</td>"
							+ "</tr>";

					for (int j = 0; j < methods.length; j++) {
						try {
							parameterTypes = methods[j].getParameterTypes();
							if (parameterTypes != null && parameterTypes.length == 0) {
								try {
									if (klass != null) {
										method = klass.getMethod(methods[j].getName(), parameterTypes);
									}
								} catch (final NoSuchMethodException e) {
									// trata exceï¿½ï¿½o caso seja construtor
									method = null;
								}

								if (method != null) {
									Object obj = null;
									try {
										obj = method.invoke(paramsOfMethod[i], (Object[]) null);
									} catch (final Exception e) {
										/* nao faz nada */
									}

									if (obj != null) {
										result += "<tr>" + "<td width=\"38%\">M&eacute;todo: " + method.getName()
												+ "</td>" + "<td colspan=\"2\">Valor: " + obj.toString() + "</td>"
												+ "</tr>";
									} else {
										result += "<tr>" + "<td width=\"38%\">M&eacute;todo: " + method.getName()
												+ "</td>" + "<td colspan=\"2\">Valor: null </td>" + "</tr>";
									}
								}
							}
						} catch (final Exception e) {
							e.printStackTrace(System.err);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Retona a classe.
	 *
	 * @return Class
	 */
	public Class<?> getClasse() {
		return classe;
	}

	/**
	 * Seta a classe.
	 *
	 * @param classe
	 */
	public void setClasse(final Class<?> classe) {
		this.classe = classe;
	}

	/**
	 * Retorna os parï¿½metros.
	 *
	 * @return Object[]
	 */
	public Object[] getParamsOfMethod() {
		return paramsOfMethod;
	}

	/**
	 * Seta os parï¿½metros.
	 *
	 * @param paramsOfMethod
	 */
	public void setParamsOfMethod(final Object[] paramsOfMethod) {
		this.paramsOfMethod = paramsOfMethod;
	}

	/**
	 * Retorna em formato string a pilha da exceï¿½ï¿½o.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder("");
		if (getCause() != null) {
			buffer.append(getCause().getLocalizedMessage()).append('\n');
			for (int i = 0; i < getCause().getStackTrace().length; i++) {
				buffer.append(getCause().getStackTrace()[i]).append('\n');
			}
		}
		return buffer.toString();
	}

	/**
	 * Retorna HTML para o stacktrace gerado pela exception
	 *
	 * @return String
	 */
	public String toHtml() {
		final StringBuilder buffer = new StringBuilder("");
		if (getCause() != null) {
			buffer.append(getCause().getLocalizedMessage()).append("<br>");
			for (int i = 0; i < getCause().getStackTrace().length; i++) {
				buffer.append(getCause().getStackTrace()[i]).append("<br>");
			}
		}
		return buffer.toString();
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	public String[] getParamsOfMessage() {
		return paramsOfMessage;
	}

	public void setParamsOfMessage(final String[] paramsOfMessage) {
		this.paramsOfMessage = paramsOfMessage;
	}
}
