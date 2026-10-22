/**
  * @author       Vincent Aumont
 * @version      $Revision: 1.1.1.1 $
 *
 */

package com.infowave.c4p.web.util;

import org.apache.struts.action.ActionForm;

/**
 * We use this form for action that do not have an associated form.
 * If we don't define a form  (with 'name attribute') in the action mapping,
 * FormTag throws an exception.
 *
 */

public final class DummyForm extends ActionForm
{
private String foo;

public String getFoo(){return foo;}
public void setFoo(String f) {foo = f;}
}