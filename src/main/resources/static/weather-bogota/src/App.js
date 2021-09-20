import React, { useState, useEffect } from "react";
import './App.css';
import Axios from 'axios';
const mystyle={
    width: "50%", 
    margin: "0 auto", 
    textAlign:"left"    
}
function App() {

const [list,setList]=useState([]);
const [idioma,setIdioma]=useState("")
const [unidades,setUnidades]=useState("");
const [weather,setWeather]=useState("");
const [wind,setWind]=useState({speed:"NA",deg:"NA"});
useEffect(() => {
    fetchData();  
}, [setList])

const fetchData = async (idioma="es",unidades="standard") => {
    try {
      const response = await Axios({
        url: `http://localhost:8080/weather?units=${unidades}&lang=${idioma}`,
      }); 
      
      setList(response.data);
      setWeather(response.data.weather[0]) 
      setWind({speed:response.data.wind.speed,deg:response.data.wind.deg})
      console.log(list)     
    } catch (error) {
      console.log(error);
    }
  };
const weatherReq= (event)=> {
    event.preventDefault()    
    fetchData(idioma,unidades);    
}

    return ( < div style={{textAlign:"center"}} className = "Clima Bogota" >
            <h1 > ¡Bienvenido! </h1>           
            <div style={mystyle} >
                <h3>Clima de Bogota:</h3>
                <form onSubmit={weatherReq}>
                    Idioma :
                    <input type="text" onChange={e=>setIdioma(e.target.value)} style={{marginLeft:"5px"}} placeholder="ejemplo:es" ></input>
                    <br></br>
                    <br></br>
                    Unidades:
                    <input type="text" onChange={e=>setUnidades(e.target.value)} style={{marginLeft:"5px"}} placeholder="ejemplo:standard" ></input>           
                    <br></br>
                    <br></br>
                    <button type="submit"  >Buscar</button>
                </form>            
                <br></br> 
            </div>

            <div style={{textAlign:"center"}} >
                <h2>General</h2>
                <p>{weather.main}</p>
                <br></br> 
                <h2>Descripcion</h2>
                <p>{weather.description}</p>
                <br></br> 
                <h2>viento</h2>
                Velocidad : {wind.speed}
                <br></br> 
                Angulo : {wind.deg}
            </div>

        </div>

    );
}

export default App;