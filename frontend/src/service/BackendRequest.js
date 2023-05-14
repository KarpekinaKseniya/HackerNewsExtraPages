import axios from 'axios';

const api = '/api/v1/hacker-news';

export const getStories = async () => {
    try {
        const result = await axios.get(api + '/new-stories');
        return result.data;
    } catch (error) {
        console.log('Error while getting list of stories.');
        console.log(error.message);
    }
};

export const getStory = async (id) => {
    try {
        const response = await axios.get(api +
            `/stories/${id}`
        );
        return response.data;
    } catch (error) {
        console.log('Error while getting a story.');
        console.log(error.message);
    }
};

export const getComment = async (id) => {
    try {
        const response = await axios.get(api +
            `/comments/${id}`
        );
        return response.data;
    } catch (error) {
        console.log('Error while getting a comment.');
        console.log(error.message);
    }
};