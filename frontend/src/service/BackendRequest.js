import axios from 'axios';

const api = '/api/v1/hacker-news';

export const getStories = async () => {
    try {
        const result = await axios.get(api + '/new-stories');
        return result.data;
    } catch (error) {
        console.log('Error while getting list of stories.');
    }
};