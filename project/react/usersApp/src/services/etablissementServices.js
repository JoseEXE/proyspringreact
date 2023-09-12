import axios from "axios";


const URL_ETABLISSEMENTS = 'http://localhost:8080/etablissements';

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
        const response = await axios.get(URL_ETABLISSEMENTS);
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;
}

export const findAllPages = async(page = 0) =>{
 
    try {
        const response = await axios.get(`${URL_ETABLISSEMENTS}/page/${page}`);
        return response;    
        
    } catch (error) {
       console.error(error);
    }
    return null;

}

export const save = async({ nom, siret, rue, cod_postal, ville, num_tel, statut }) =>{
    try {
        return await axios.post(URL_ETABLISSEMENTS, { nom, siret, rue, cod_postal, ville, num_tel, statut, }, config());
    } catch (error) {
       // console.error(error);
       throw error; 
    }
   // return undefined;
}

export const update = async({ id, nom, siret, rue, cod_postal, ville, num_tel, statut }) => {
    try {
        return await axios.put(`${URL_ETABLISSEMENTS}/${id}`, { nom, siret, rue, cod_postal, ville, num_tel, statut, }, config());
    } catch (error) {
       // console.error(error);
       throw error;     
    }
   // return undefined;
}
