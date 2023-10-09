export const commandeReducer = (state = {}, action) =>{

    switch (action.type) {
        case 'addRegistre':
            return [
                ...state,
                {	
                    ...action.payload,
                }
            ];
        
        case 'updateRegistre':

            return state.map(u => {
                if(u.id === action.payload.id){
                    return {
                        ...action.payload
                    };

                }
                return u;
            });

        case 'loading':
            return action.payload.content;
            
        case 'loadingPagination':
            return action.payload;

        case 'loadingLast':
            return action.payload;

        case 'loadingselect':
            return action.payload;
                
        
        default:
            return state;
    }

}