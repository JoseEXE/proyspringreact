import axios from "axios";
const URL = 'http://localhost:8080/clients';

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
        const response = await axios.get(URL);
        return response;    
    } catch (error) {
       console.error(error);
    }
    return null;
}

export const findAllPages = async(page = 0) =>{
 
    try {
        const response = await axios.get(`${URL}/page/${page}`);
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;
}

export const findByTel = async({ tel }) =>{
 
    try {
        const response = await axios.post(`${URL}/recherche`, { tel }, config());
        
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;
}


export const save = async({ nom, prenom, num_tel, statut }) =>{
    try {
        return await axios.post(URL, {  nom, prenom, num_tel, statut  }, config());
    } catch (error) {
       throw error; 
    }
}



export const update = async({ id,  nom, prenom, num_tel, statut  }) => {
    try {
        return await axios.put(`${URL}/${id}`, { nom, prenom, num_tel, statut }, config());        
    } catch (error) {
        console.log(error);
       throw error;     
    }
}
