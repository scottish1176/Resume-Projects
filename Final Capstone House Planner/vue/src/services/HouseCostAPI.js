import axios from "axios";
export default {


    getCostOfHouse(house) {
        // let estimatedCost = 5;
        const options = {
            method: 'GET',
            url: 'https://us-real-estate.p.rapidapi.com/v2/for-sale',
            params: {
                offset: '0',
                limit: '1',
                state_code: house.state_code,
                city: house.houseParamsCity,
                sort: 'newest',
                beds_min: house.bedrooms,
                baths_min: house.bathrooms,
                property_type: 'single_family',
                home_size_min: house.size,
                stories: house.stories
            },
            headers: {
                'X-RapidAPI-Host': 'us-real-estate.p.rapidapi.com',
                //'X-RapidAPI-Key': 'KEY GOES HERE, it's a paid API so I"m not going to hand it out for free:)'
            }
        };
        return axios.request(options)
        // axios.request(options).then(function (response) {
        //     console.log(response.data.data.home_search.results[0].list_price);
        //     // console.log(estimatedCost)
        //     // estimatedCost = (response.data.data.home_search.results[0].list_price);

        // }).catch(function (error) {
        //     console.error(error);
        // })
        // console.log(estimatedCost)
        // return estimatedCost;
    }
}
