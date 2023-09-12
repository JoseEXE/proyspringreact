export const etablissementReducer = (state = {}, action) =>{

    switch (action.type) {
        case 'addEtablissement':
            return [
                ...state,
                {	
                    ...action.payload,
                   // id: new Date().getTime(),
                }
            ];
        
        case 'updateEtablissement':

            return state.map(u => {
                if(u.id === action.payload.id){
                    return {
                        ...action.payload
                    };

                }
                return u;
            });

        case 'loadingEtablissement':
            return action.payload.content;
        
        case 'loadingEtablissementPagination':
            return action.payload;
                
    
        
        default:
            return state;
    }

}