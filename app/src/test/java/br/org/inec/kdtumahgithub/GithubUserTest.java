package br.org.inec.kdtumahgithub;

import junit.framework.TestCase;

import org.junit.Test;

import br.org.inec.kdtumahgithub.data.GithubUser;

/**
 * Created by tiagosombra on 28/12/17.
 */

public class GithubUserTest extends TestCase{

    @Test
    public void EmptyGithubUser(){
        GithubUser user = new GithubUser();
        user.setLogin("loginTest");
        assertEquals("loginTest", user.getLogin());
    }

}
