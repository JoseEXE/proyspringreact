import axios from "axios";


const URL_CATPRODUIT = 'http://localhost:8080/catproduits';

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
        const response = await axios.get(URL_CATPRODUIT);
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;
}

export const findAllPages = async(page = 0) =>{
 
    try {
        const response = await axios.get(`${URL_CATPRODUIT}/page/${page}`);
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;
}


export const save = async({ nom, description, statut }) =>{
    try {
        return await axios.post(URL_CATPRODUIT, { nom, description, statut, }, config());
    } catch (error) {
       // console.error(error);
       throw error; 
    }
   // return undefined;
}

export const update = async({ id, nom, description, statut, }) => {
    try {
        return await axios.put(`${URL_CATPRODUIT}/${id}`, { nom, description, statut  }, config());
    } catch (error) {
       // console.error(error);
       throw error;     
    }
   // return undefined;
}
