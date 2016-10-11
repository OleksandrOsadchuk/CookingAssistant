package com.oleksandr.cookingassistant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oleksandr on 16-10-09.
 */
public class GsonParser {

    public List parseJson(String jsonString) {

        JsonObject jo = (JsonObject) new JsonParser().parse(jsonString);
        JsonArray itemJsonArr = jo.getAsJsonArray("items");


            for (JsonElement je: itemJsonArr) {
                JsonObject obj = je.getAsJsonObject();
                Recipe r = new Recipe();
                //r.setTitle((String)obj.getAsJsonPrimitive("item"));
            }


        Type type = new TypeToken<CseResult>(){}.getType();
        Gson gson = new Gson();
        List<CseResult> cseItemsList = gson.fromJson(jo, type);
        return cseItemsList;
    }



    public class CseResult {
        String kind;
        String title;
        String cacheId;
        String displayLink;
        String formattedUrl;
        String htmlFormattedUrl;
        String htmlSnippet;
        String htmlTitle;
        String link;
        PageMap pagemap;
        String snippet;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCacheId() {
            return cacheId;
        }

        public void setCacheId(String cacheId) {
            this.cacheId = cacheId;
        }

        public String getDisplayLink() {
            return displayLink;
        }

        public void setDisplayLink(String displayLink) {
            this.displayLink = displayLink;
        }

        public String getFormattedUrl() {
            return formattedUrl;
        }

        public void setFormattedUrl(String formattedUrl) {
            this.formattedUrl = formattedUrl;
        }

        public String getHtmlFormattedUrl() {
            return htmlFormattedUrl;
        }

        public void setHtmlFormattedUrl(String htmlFormattedUrl) {
            this.htmlFormattedUrl = htmlFormattedUrl;
        }

        public String getHtmlSnippet() {
            return htmlSnippet;
        }

        public void setHtmlSnippet(String htmlSnippet) {
            this.htmlSnippet = htmlSnippet;
        }

        public String getHtmlTitle() {
            return htmlTitle;
        }

        public void setHtmlTitle(String htmlTitle) {
            this.htmlTitle = htmlTitle;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public PageMap getPagemap() {
            return pagemap;
        }

        public void setPagemap(PageMap pagemap) {
            this.pagemap = pagemap;
        }

        public String getSnippet() {
            return snippet;
        }

        public void setSnippet(String snippet) {
            this.snippet = snippet;
        }


    }

    public class PageMap {
        String[] aggregaterating;
        String[] breadcrumb;
        String[] cse_image;
        String[] cse_thumbnail;
        String[] metatags;
        String[] nutritioninformation;
        String[] rating;
        String[] recipe;
        String[] review;
        String[] videoobject;

        public String[] getAggregaterating() {
            return aggregaterating;
        }

        public void setAggregaterating(String[] aggregaterating) {
            this.aggregaterating = aggregaterating;
        }

        public String[] getBreadcrumb() {
            return breadcrumb;
        }

        public void setBreadcrumb(String[] breadcrumb) {
            this.breadcrumb = breadcrumb;
        }

        public String[] getCse_image() {
            return cse_image;
        }

        public void setCse_image(String[] cse_image) {
            this.cse_image = cse_image;
        }

        public String[] getCse_thumbnail() {
            return cse_thumbnail;
        }

        public void setCse_thumbnail(String[] cse_thumbnail) {
            this.cse_thumbnail = cse_thumbnail;
        }

        public String[] getMetatags() {
            return metatags;
        }

        public void setMetatags(String[] metatags) {
            this.metatags = metatags;
        }

        public String[] getNutritioninformation() {
            return nutritioninformation;
        }

        public void setNutritioninformation(String[] nutritioninformation) {
            this.nutritioninformation = nutritioninformation;
        }

        public String[] getRating() {
            return rating;
        }

        public void setRating(String[] rating) {
            this.rating = rating;
        }

        public String[] getRecipe() {
            return recipe;
        }

        public void setRecipe(String[] recipe) {
            this.recipe = recipe;
        }

        public String[] getReview() {
            return review;
        }

        public void setReview(String[] review) {
            this.review = review;
        }

        public String[] getVideoobject() {
            return videoobject;
        }

        public void setVideoobject(String[] videoobject) {
            this.videoobject = videoobject;
        }
    }


}
