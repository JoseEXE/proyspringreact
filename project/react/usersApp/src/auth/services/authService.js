import axios from "axios";

export const loginUser = async({ username, password}) => {

    const newPost = {
        email: username,
        password: password
    };

    try {
     
        
        return await axios.post('http://localhost:8080/login', newPost);
        
    } catch (error) {
        
        throw error;

    }
}