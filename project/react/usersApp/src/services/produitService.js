import axios from "axios";
const URL = 'http://localhost:8080/produits';

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


export const save = async({ catProduit, user, code, nom, description, prix, type_plat }) =>{
    try {
        return await axios.post(URL, { catProduit , user, code, nom, description, prix, type_plat }, config());
    } catch (error) {
       throw error; 
    }
}

export const update = async({ id, catProduit, user, code, nom, description, prix, type_plat  }) => {
    try {

        return await axios.put(`${URL}/${id}`, {catProduit, user, code, nom, description, prix, type_plat }, config());
    } catch (error) {
       throw error;     
    }
}
