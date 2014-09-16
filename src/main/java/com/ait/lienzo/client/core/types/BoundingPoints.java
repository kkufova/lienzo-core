/*
   Copyright (c) 2014 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.lienzo.client.core.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public final class BoundingPoints implements Iterable<Point2D>
{
    private final Point2DArray m_array = new Point2DArray();

    public BoundingPoints(BoundingBox bbox)
    {
        double x = bbox.getX();

        double y = bbox.getY();

        double w = bbox.getWidth();

        double h = bbox.getHeight();

        m_array.push(new Point2D(x + 0, y + 0));

        m_array.push(new Point2D(x + w, y + 0));

        m_array.push(new Point2D(x + w, y + h));

        m_array.push(new Point2D(x + 0, y + h));
    }

    public final BoundingPoints transform(Transform transform)
    {
        int leng = m_array.size();

        for (int i = 0; i < leng; i++)
        {
            Point2D p = m_array.get(i);

            transform.transform(p, p);
        }
        return this;
    }

    public final BoundingBox getBoundingBox()
    {
        return m_array.getBoundingBox();
    }

    public final Collection<Point2D> getPoints()
    {
        int leng = m_array.size();

        ArrayList<Point2D> list = new ArrayList<Point2D>(leng);

        for (int i = 0; i < leng; i++)
        {
            list.add(m_array.get(i));
        }
        return Collections.unmodifiableCollection(list);
    }

    public final String toJSONString()
    {
        return m_array.toJSONString();
    }

    @Override
    public final String toString()
    {
        return toJSONString();
    }

    @Override
    public final Iterator<Point2D> iterator()
    {
        return getPoints().iterator();
    }
}