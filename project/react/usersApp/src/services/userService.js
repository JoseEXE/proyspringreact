import axios from "axios";

const URL_USERS = 'http://localhost:8080/users';

const config = () => {
    return {
        headers : {
            "Authorization" : sessionStorage.getItem('token'),
            "Content-Type" : "application/json",
    
        }
    
    }
}

export const findAll = async() =>{
 
    try {
        const response = await axios.get(URL_USERS);
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;
}

export const findAllPages = async(page = 0) =>{
 
    try {
        const response = await axios.get(`${URL_USERS}/page/${page}`);
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;

}

export const save = async({ nameU, lastName, email, password, admin }) =>{
    try {
        return await axios.post(URL_USERS, { nameU, lastName, email, password, admin,}, config());
    } catch (error) {
       // console.error(error);
       throw error; 
    }
   // return undefined;
}

export const update = async({ id, nameU, lastName, email, admin }) => {
    try {
        return await axios.put(`${URL_USERS}/${id}`, { nameU, lastName, email, admin, }, config());
    } catch (error) {
       // console.error(error);
       throw error;     
    }
   // return undefined;
}

export const remove = async(id) => {
    try {
        return await axios.delete(`${URL_USERS}/${id}`, config());
    } catch (error) {
       // console.error(error);
       throw error; 
    }
   // return undefined;
}


export const findMyUser = async(id) =>{
    try {
        console.log("entre userService");
        const response = await axios.get(`${URL_USERS}/pass/${id}`, config());
        return response;    
        
    } catch (error) {
        console.error(error);
    }
}


export const changePass = async({ id, oldPassword, newPassword }) => {

    const userPass = {
        oldPassword: oldPassword,
        newPassword: newPassword
    };

    try {
        return await axios.put(`${URL_USERS}/pass/${id}`, userPass, config());
        console.log("Ok SQL");
    } catch (error) {
       console.error(error);
       throw error;     
    }

}