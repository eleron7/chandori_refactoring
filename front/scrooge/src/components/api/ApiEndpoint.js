const API_BASE_URL = process.env.REACT_APP_requestUrl;


export const ApiEndpoint = {
  join: `${API_BASE_URL}/api/join`,
  login : `${API_BASE_URL}/api/login`,
};

export default ApiEndpoint;