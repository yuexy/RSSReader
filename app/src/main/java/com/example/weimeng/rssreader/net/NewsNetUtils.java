package com.example.weimeng.rssreader.net;

import com.example.weimeng.rssreader.db.CustomDatabaseUtils;
import com.example.weimeng.rssreader.nodes.News;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by weimeng on 2016/5/24.
 */
public class NewsNetUtils
{
	public static List<News> getNews(String url)
	{
		List<News> newsList = null;

		String res = CustomNetUtils.downloadByGET(url);
		if (null == res)
			return null;

		try
		{
			newsList = processString(res);
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}

		//System.out.println(res);

		return newsList;
	}

	public static List<News> processString(String res) throws ParserConfigurationException, IOException, SAXException
	{
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(res));

		Document doc = db.parse(is);

		Element treeElements = doc.getDocumentElement();

		NodeList nodeList = treeElements.getElementsByTagName("item");

		List<News> newsList = new ArrayList<>();

		for (int i = 0; i < nodeList.getLength(); i++)
		{
			News news = new News();

			Element newsNode = (Element) nodeList.item(i);

			NodeList newsChildNodes = newsNode.getChildNodes();

			for (int j = 0; j < newsChildNodes.getLength(); j++)
			{
				Node node = newsChildNodes.item(j);

				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					if ("title".equals(element.getNodeName()))
					{
						news.topic = element.getFirstChild().getNodeValue();
					}
					if ("description".equals(element.getNodeName()))
					{
						news.des = element.getFirstChild().getNodeValue();
					}
					if ("pubDate".equals(element.getNodeName()))
					{
						news.time = element.getFirstChild().getNodeValue();
					}
					if ("link".equals(element.getNodeName()))
					{
						news.add = element.getFirstChild().getNodeValue();
					}
				}
			}

			newsList.add(news);
		}

		return newsList;
	}
}
