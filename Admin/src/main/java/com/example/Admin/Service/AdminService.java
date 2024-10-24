package com.example.Admin.Service;

import com.example.Admin.Model.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllStudents();
    void saveEmployee(Admin admin);
    Admin getStudentById(long id);
    void deleteStudentById(long id);
    String getPass(Admin admin);
    Admin getStudentByEmail(String email);
    String generateCommonTextPassword();
    public Admin updateStudent(Admin admin);

}

/*
<div class="container">
    <h1>Login Details</h1>
    <hr>
    <form action="login" method="post">
        <table>
            <tr>
                <td> User</td>
                <td> <input type="text" name="username" value=""></td>
            </tr>
            <tr>
                <td>Password</td>
                <td> <input type="password" name="password" /></td>
            </tr>
            <tr>
                <td> <input name="submit" type="submit"  value="Login" class="btn btn-primary btn-sm mb-3"/></td>
                <td><a th:href = "@{/register}" class="btn btn-primary btn-sm mb-3"> Register </a></td>
                <td><a th:href = "@{/forgot}" class="btn btn-primary btn-sm mb-3"> Forgot Password </a></td>
            </tr>

        </table>
    </form>
</div>
 */